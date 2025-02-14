/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiendalibros;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.AbstractListModel;
import net.sf.clipsrules.jni.Environment;
import net.sf.clipsrules.jni.FactAddressValue;
import net.sf.clipsrules.jni.LexemeValue;
import net.sf.clipsrules.jni.MultifieldValue;

/**
 *
 * @author pablo
 */
public class Vcliente extends javax.swing.JFrame {

    private Environment clips;
    private Vinicio vi;
    private HashMap<String, Libro> lista_libros;    //Lisa de libros con atributos leiod sde CLIPS
    private String cliente;   //nombre del cliente pasado en pantalla anterior
    private ArrayList<String> carrito;     
    
    public Vcliente(Vinicio vi, Environment clips, String nombre) {
        initComponents();
        this.setLocationRelativeTo(null);
        this.clips = clips;
        this.vi = vi;
        this.carrito = new ArrayList<>();
        this.cliente = nombre;
        lista_libros = new HashMap<>();
        this.setVisible(true);
        this.setResizable(false);
        this.actualizarLibros();
        
        //not editable
        titulo.setEditable(false);
        autor.setEditable(false);
        genero.setEditable(false);
        tematica.setEditable(false);
        movimiento_literario.setEditable(false);
        publico.setEditable(false);
        epoca_historica.setEditable(false);
        recomendationPanel.setEditable(false);
    }
    
    public void actualizarLibros(){
        try{
            //obtenemos todos los libros.
            String evalStr = "(find-all-facts ((?l Libro)) TRUE)";
            MultifieldValue mv = (MultifieldValue) clips.eval(evalStr);
            
            
            String[] aux = new String[mv.size()];
            for (int i = 0; i <= mv.size()-1; i++){
                FactAddressValue fv = (FactAddressValue) mv.get(i);
                aux[i] = ((LexemeValue) fv.getFactSlot("titulo")).lexemeValue();
                Libro libro_ = new Libro(aux[i]);
                
                //resto de propiedades:
                libro_.setAutor(((LexemeValue) fv.getFactSlot("autor")).lexemeValue());
                libro_.setGenero(((LexemeValue) fv.getFactSlot("genero")).lexemeValue());
                libro_.setTematica(((LexemeValue) fv.getFactSlot("tematica")).lexemeValue());
                libro_.setTipo_publico(((LexemeValue) fv.getFactSlot("tipo_de_publico")).lexemeValue());
                libro_.setMovimiento_literario(((LexemeValue) fv.getFactSlot("movimiento_literario")).lexemeValue());
                libro_.setEpoca_historica(((LexemeValue) fv.getFactSlot("epoca_historica")).lexemeValue());
                lista_libros.put(aux[i], libro_);
                //System.out.println(aux[i]);
            }
            //actualizamos lista
            libros.setListData(aux);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        libros = new javax.swing.JList<>();
        jLabel2 = new javax.swing.JLabel();
        titulo = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        autor = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        genero = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        tematica = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        movimiento_literario = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        publico = new javax.swing.JTextField();
        comprar = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        epoca_historica = new javax.swing.JTextField();
        anadir = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        recomendationPanel = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel4.setBackground(new java.awt.Color(235, 217, 200));

        jPanel5.setBackground(new java.awt.Color(254, 160, 47));

        libros.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 1", "Item 2", "Item 3", "Item 4", "Item 5", " " };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        libros.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                librosValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(libros);

        jLabel2.setFont(new java.awt.Font("Cantarell", 0, 18)); // NOI18N
        jLabel2.setText("Título: ");

        titulo.setFont(new java.awt.Font("Cantarell", 0, 18)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Cantarell", 0, 18)); // NOI18N
        jLabel1.setText("Autor: ");

        autor.setFont(new java.awt.Font("Cantarell", 0, 18)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Cantarell", 0, 18)); // NOI18N
        jLabel4.setText("Género:");

        genero.setFont(new java.awt.Font("Cantarell", 0, 18)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Cantarell", 0, 18)); // NOI18N
        jLabel5.setText("Tema:");

        tematica.setFont(new java.awt.Font("Cantarell", 0, 18)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Cantarell", 0, 18)); // NOI18N
        jLabel6.setText("Pub:");

        movimiento_literario.setFont(new java.awt.Font("Cantarell", 0, 18)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Cantarell", 0, 18)); // NOI18N
        jLabel7.setText("Mov. lit:");

        publico.setFont(new java.awt.Font("Cantarell", 0, 18)); // NOI18N

        comprar.setBackground(new java.awt.Color(0, 63, 90));
        comprar.setFont(new java.awt.Font("Cantarell", 1, 18)); // NOI18N
        comprar.setForeground(new java.awt.Color(254, 254, 254));
        comprar.setText("COMPRAR");
        comprar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comprarActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Cantarell", 0, 18)); // NOI18N
        jLabel8.setText("Ep. hist:");

        epoca_historica.setFont(new java.awt.Font("Cantarell", 0, 18)); // NOI18N

        anadir.setBackground(new java.awt.Color(0, 63, 90));
        anadir.setFont(new java.awt.Font("Cantarell", 1, 18)); // NOI18N
        anadir.setForeground(new java.awt.Color(254, 254, 254));
        anadir.setText("AÑADIR A CARRITO");
        anadir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                anadirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(genero, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tematica, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(autor)
                            .addComponent(titulo)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(movimiento_literario, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(publico, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel5Layout.createSequentialGroup()
                            .addComponent(anadir)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(comprar))
                        .addGroup(jPanel5Layout.createSequentialGroup()
                            .addComponent(jLabel8)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(epoca_historica, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(titulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(autor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(genero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(tematica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(movimiento_literario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(publico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(epoca_historica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(comprar)
                            .addComponent(anadir))
                        .addGap(0, 9, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );

        jButton4.setBackground(new java.awt.Color(0, 63, 90));
        jButton4.setFont(new java.awt.Font("Cantarell", 1, 24)); // NOI18N
        jButton4.setForeground(new java.awt.Color(254, 254, 254));
        jButton4.setText("SALIR   X");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(0, 63, 90));
        jButton5.setFont(new java.awt.Font("Cantarell", 1, 24)); // NOI18N
        jButton5.setForeground(new java.awt.Color(254, 254, 254));
        jButton5.setText("<   ATRÁS");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        recomendationPanel.setBackground(new java.awt.Color(0, 63, 94));
        recomendationPanel.setColumns(20);
        recomendationPanel.setForeground(new java.awt.Color(254, 254, 254));
        recomendationPanel.setRows(5);
        jScrollPane2.setViewportView(recomendationPanel);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // cerrar TODO
        clips.destroy();
        System.exit(0);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        vi.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton5ActionPerformed
    
    /*AL SELECCIONAL UN ELEMENTO DE LA LISTA MOSTRAMOS INFORMACIÓN DE ESE LIBRO*/
    private void librosValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_librosValueChanged
        // TODO add your handling code here:
        try{
        String tit_aux = libros.getSelectedValue();
        titulo.setText(lista_libros.get(tit_aux).getTitulo());
        autor.setText(lista_libros.get(tit_aux).getAutor());
        genero.setText(lista_libros.get(tit_aux).getGenero());
        tematica.setText(lista_libros.get(tit_aux).getTematica());
        movimiento_literario.setText(lista_libros.get(tit_aux).getMovimiento_literario());
        publico.setText(lista_libros.get(tit_aux).getTipo_publico());
        epoca_historica.setText(lista_libros.get(tit_aux).getEpoca_historica());
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_librosValueChanged

    private void comprarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comprarActionPerformed
        // TODO add your handling code here:
        //añadimos libro en los libros del cliente. Si cliente no existe ya.
        boolean existe = false;
        String libro_comprado = libros.getSelectedValue();
        try{
            String evalStr = "(find-all-facts ((?c Cliente)) TRUE)";
            MultifieldValue mv = (MultifieldValue) clips.eval(evalStr);
            
            for (int i = 0; i <= mv.size()-1; i++){
                FactAddressValue fv = (FactAddressValue) mv.get(i);
                String id_aux = ((LexemeValue) fv.getFactSlot("id")).lexemeValue();
                if(id_aux.equals(cliente)){
                    existe=true;
                }
            }
            if(existe==false){
                //añadimos hecho del cliente con sus libros
                String aux_ = new String();
                for(String aux: carrito){
                    aux_ = aux_ + aux + " ";
                }
                
                clips.assertString("(Cliente (id "+cliente+")(edad 21)(libros_comprados "+aux_+"))");
                //ejecutamos motor de recomendación.
                CaptureRouter router = new CaptureRouter(recomendationPanel);
                clips.addRouter(router);
                clips.run();
            }
            
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_comprarActionPerformed

    private void anadirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_anadirActionPerformed
        // TODO add your handling code here:
        String libro_seleccionado = "\"" + libros.getSelectedValue() + "\"";
        carrito.add(libro_seleccionado);
        lista_libros.remove(libros.getSelectedValue());
        String[] aux = new String[lista_libros.size()];
        int i=0;
        for (Map.Entry<String, Libro> entry : lista_libros.entrySet()) {
            aux[i] = entry.getKey();
            i++;
        }
        libros.setListData(aux);
        
    }//GEN-LAST:event_anadirActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton anadir;
    private javax.swing.JTextField autor;
    private javax.swing.JButton comprar;
    private javax.swing.JTextField epoca_historica;
    private javax.swing.JTextField genero;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList<String> libros;
    private javax.swing.JTextField movimiento_literario;
    private javax.swing.JTextField publico;
    private javax.swing.JTextArea recomendationPanel;
    private javax.swing.JTextField tematica;
    private javax.swing.JTextField titulo;
    // End of variables declaration//GEN-END:variables
}
