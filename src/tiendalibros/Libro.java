/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiendalibros;

import java.util.Objects;

/**
 *
 * @author pablo
 */
public class Libro {
    private String titulo;
    private String autor;
    private String genero;
    private String tematica;
    private String anho;
    private String movimiento_literario;
    private String tipo_publico;
    private String epoca_historica;

    public Libro(String titulo) {
        this.titulo = titulo;
    }

    public String getEpoca_historica() {
        return epoca_historica;
    }

    public void setEpoca_historica(String epoca_historica) {
        this.epoca_historica = epoca_historica;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getTematica() {
        return tematica;
    }

    public void setTematica(String tematica) {
        this.tematica = tematica;
    }

    public String getAnho() {
        return anho;
    }

    public void setAnho(String anho) {
        this.anho = anho;
    }

    public String getMovimiento_literario() {
        return movimiento_literario;
    }

    public void setMovimiento_literario(String movimiento_literario) {
        this.movimiento_literario = movimiento_literario;
    }

    public String getTipo_publico() {
        return tipo_publico;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.titulo);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Libro other = (Libro) obj;
        if (!Objects.equals(this.titulo, other.titulo)) {
            return false;
        }
        return true;
    }

    public void setTipo_publico(String tipo_publico) {
        this.tipo_publico = tipo_publico;
    }
}
