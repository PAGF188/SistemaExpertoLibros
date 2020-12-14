; -----------------------------------------------
; 			PLANTILLA PARA CLIENTE
; ----------------------------------------------- 

(deftemplate Cliente
	(field id (default ?NONE))
	(field edad (type INTEGER)(range -1 ?VARIABLE)(default -1))
	(field tipo_de_publico (type SYMBOL)(allowed-symbols infantil juvenil adulto nil)(default nil))
	(multifield libros_comprados)
	(multifield libros_recomendados)
)

; -----------------------------------------------
; 			  PLANTILLA PARA LIBRO
; -----------------------------------------------

(deftemplate Libro
	(field titulo (default nil))
	(field autor (default nil))
	(field genero (type SYMBOL)(allowed-symbols narrativo lirico dramatico didactico nil)(default nil))
	(field tematica (type SYMBOL)(default nil))
	(field anho (type INTEGER)(default -2000))
	(field epoca_historica (type SYMBOL)(allowed-symbols antigua media moderna contemporanea nil)(default nil))
	(field movimiento_literario (type SYMBOL)(default nil))
	(field tipo_de_publico (type SYMBOL)(allowed-symbols infantil juvenil adulto nil)(default nil))
)



; -----------------------------------------------
; 					REGLAS
; -----------------------------------------------

; ------------------------------------------------------------------------
; reglas para inferir tipo_de_publico de un cliente en función de la edad
; ------------------------------------------------------------------------

; comprobaacion previa con -1 -> para saber si la edad fue insertada

(defrule tipo_infantil
	?c <-(Cliente (tipo_de_publico nil)(edad ?e))
	(test (neq ?e -1))
	(test (<= ?e 9))
	=>
	(modify ?c(tipo_de_publico infantil))
)

(defrule tipo_juvenil
	?c <-(Cliente (tipo_de_publico nil)(edad ?e))
	(test (neq ?e -1))
	(test (> ?e 9))
	(test (< ?e 18))
	=>
	(modify ?c(tipo_de_publico juvenil))
)

(defrule tipo_adulto
	?c <-(Cliente (tipo_de_publico nil)(edad ?e))
	(test (neq ?e -1))
	(test (>= ?e 18))
	=>
	(modify ?c(tipo_de_publico adulto))
)


; ---------------------------------------------------------------------------------
; reglas para inferir epoca_historica de un libro en función del año de publicación
; ---------------------------------------------------------------------------------

; comprobaacion previa con -2000 -> para saber si el año fue insertado

(defrule epoca_historica_antigua
	?l <-(Libro (epoca_historica nil)(anho ?a))
	(test (neq ?a -2000))
	(test (<= ?a 476))
	=>
	(modify ?l(epoca_historica antigua))
)

(defrule epoca_historica_media
	?l <-(Libro (epoca_historica nil)(anho ?a))
	(test (neq ?a -2000))
	(test (> ?a 476))
	(test (<= ?a 1492))
	=>
	(modify ?l(epoca_historica media))
)

(defrule epoca_historica_moderna
	?l <-(Libro (epoca_historica nil)(anho ?a))
	(test (neq ?a -2000))
	(test (> ?a 1492))
	(test (<= ?a 1789))
	=>
	(modify ?l(epoca_historica moderna))
)

(defrule epoca_historica_contemporanea
	?l <-(Libro (epoca_historica nil)(anho ?a))
	(test (neq ?a -2000))
	(test (> ?a 1789))
	=>
	(modify ?l(epoca_historica contemporanea))
)

; ---------------------------------------------------------------------------------
; REGLAS DE RECOMENDACIÓN
; ---------------------------------------------------------------------------------
; todas estas reglas se pueden ejecutar si ya se ha procesado tipo_de_publico


; REGLA8 -> PORQUE HAS LEÍDO LIBROS DEL MISMO AUTOR:
; condiciones:
;	- Mismo autor
;	- dirigido a tu tipo_de_publico 

; ?libro_comprado es el titulo de un libro comprado por el cliente.
; ?lista_comprados y ?lista_recomendados respectivas listas de titulos. ?publico -> tu edad categorizada
; ?autor_ es el autor del libro comprado a evaluar. 
; ?libro_recomendado va tomando el valor de todos los posibles libros escritos por ?autor_
; Finalmente comprobamos que el libro recomendado no este ya insertado en recomendaciones o comprados.

(defrule r8_autores
	?cliente <-(Cliente (id ?x)(tipo_de_publico ~nil)(libros_comprados $? ?libro_comprado $?))
	(Cliente (id ?x)(libros_comprados $?lista_comprados)(libros_recomendados $?lista_recomendados)(tipo_de_publico ?publico))
	
	; para obtener autor
	(Libro (titulo ?libro_comprado)(autor ?autor_))
	
	; otros libros escritos por ese mismo autor y dirigidos a tu tipo de público.
	(Libro (titulo ?libro_recomendado)(autor ?autor_)(tipo_de_publico ?publico))
	
	; no insertado ya
	(test (not (member$ ?libro_recomendado ?lista_comprados)))
	(test (not (member$ ?libro_recomendado ?lista_recomendados)))
	=>
	(printout t "Porque has leido libros de " ?autor_ " te recomendamos: " ?libro_recomendado crlf)
	(modify ?cliente (libros_recomendados(insert$ ?lista_recomendados 1  ?libro_recomendado)))
)

; REGLA9 -> PORQUE HAS LEÍDO LIBROS DEL MISMO GENERO Y TEMATICA
; condiciones:
;	- Mismo genero y misma temática.
;	- dirigido a tu tipo_de_publico.

(defrule r9_genyt
	?cliente <-(Cliente (id ?x)(tipo_de_publico ~nil)(libros_comprados $? ?libro_comprado $?))
	(Cliente (id ?x)(libros_comprados $?lista_comprados)(libros_recomendados $?lista_recomendados)(tipo_de_publico ?publico))
	
	; para obtener genero y temática
	(Libro (titulo ?libro_comprado)(genero ?genero_)(tematica ?tematica_))
	
	; otros libros escritos con el mismo genero y temática y dirigidos a tu tipo de público.
	(Libro (titulo ?libro_recomendado)(genero ?genero_)(tematica ?tematica_)(tipo_de_publico ?publico))
	
	; no insertado ya
	(test (not (member$ ?libro_recomendado ?lista_comprados)))
	(test (not (member$ ?libro_recomendado ?lista_recomendados)))
	=>
	(printout t "Porque has leido libros de genero " ?genero_ " y tematica " ?tematica_ " te recomendamos: " ?libro_recomendado crlf)
	(modify ?cliente (libros_recomendados(insert$ ?lista_recomendados 1  ?libro_recomendado)))
)

; REGLA10 -> PORQUE HAS LEÍDO LIBROS DEl MISMO MOVIMIENTO LITERARIO Y GÉNERO
; condiciones:
;	- Mismo genero y movimiento_literario.
;	- dirigido a tu tipo_de_publico.

(defrule r10_movyg
	?cliente <-(Cliente (id ?x)(tipo_de_publico ~nil)(libros_comprados $? ?libro_comprado $?))
	(Cliente (id ?x)(libros_comprados $?lista_comprados)(libros_recomendados $?lista_recomendados)(tipo_de_publico ?publico))
	
	; para obtener genero y movimiento_literario
	(Libro (titulo ?libro_comprado)(genero ?genero_)(movimiento_literario ?movimiento_literario_))
	
	; otros libros escritos con el mismo genero y movimiento_literario y dirigidos a tu tipo de público.
	(Libro (titulo ?libro_recomendado)(genero ?genero_)(movimiento_literario ?movimiento_literario_)(tipo_de_publico ?publico))
	
	; no insertado ya
	(test (not (member$ ?libro_recomendado ?lista_comprados)))
	(test (not (member$ ?libro_recomendado ?lista_recomendados)))
	=>
	(printout t "Porque has leido libros de genero " ?genero_ " del movimiento " ?movimiento_literario_ " te recomendamos: " ?libro_recomendado crlf)
	(modify ?cliente (libros_recomendados(insert$ ?lista_recomendados 1  ?libro_recomendado)))
)



; REGLA 11 -> PORQUE HAS LEIDO LIBROS DE LA MISMA EPOCA HISTORICA Y TEMATICA
; condiciones:
;	- Misma epoca historia y tematica.
;	- dirigido a tu tipo_de_publico.

(defrule r11_epocyt
	?cliente <-(Cliente (id ?x)(tipo_de_publico ~nil)(libros_comprados $? ?libro_comprado $?))
	(Cliente (id ?x)(libros_comprados $?lista_comprados)(libros_recomendados $?lista_recomendados)(tipo_de_publico ?publico))
	
	; para obtener epoca_historica y tematica
	(Libro (titulo ?libro_comprado)(epoca_historica ?epoc)(tematica ?tematica_))
	
	; otros libros escritos con el mismo genero y movimiento_literario y dirigidos a tu tipo de público.
	(Libro (titulo ?libro_recomendado)(epoca_historica ?epoc)(tematica ?tematica_)(tipo_de_publico ?publico))
	
	; no insertado ya
	(test (not (member$ ?libro_recomendado ?lista_comprados)))
	(test (not (member$ ?libro_recomendado ?lista_recomendados)))
	=>
	(printout t "Porque has leido libros de la epoca " ?epoc " con tematica " ?tematica_ " te recomendamos: " ?libro_recomendado crlf)
	(modify ?cliente (libros_recomendados(insert$ ?lista_recomendados 1  ?libro_recomendado)))
)