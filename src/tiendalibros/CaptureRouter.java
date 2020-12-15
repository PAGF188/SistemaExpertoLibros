/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiendalibros;

import net.sf.clipsrules.jni.Router;

/**
 *
 * @author pablo
 */
public class CaptureRouter implements Router{
    
    private int veces;
    public javax.swing.JTextArea recomendationPanel;
    
    public CaptureRouter(javax.swing.JTextArea recomendationPanel){
        super();
        this.recomendationPanel=recomendationPanel;
        veces=0;
    }
    
    @Override
    public int getPriority()
       {
        return 10;
       }

    @Override
    public String getName()
        {
        return "grab";
        }
    @Override
      public boolean query(
        String logName)
        {    
         if (logName.equals("wwarning") ||
             logName.equals("werror") ||
             logName.equals("wtrace") ||
             logName.equals("wdialog") ||
             logName.equals("wprompt") ||
             logName.equals("wdisplay") ||
             logName.equals("stdout"))
           { return true; }  

         return false;
        }
    @Override
      public void print(
         String routerName,
         String printString)
         {
            System.out.println(printString);
            String aux = recomendationPanel.getText();
            if(veces%4==0){
                recomendationPanel.setText(aux+"\n"+printString);
            }else{
                recomendationPanel.setText(aux + printString);
            }
            veces++;
         }
    @Override
      public int getchar(
        String routerName)
        {
         return -1;
        }
    @Override
      public int ungetchar(
        String routerName,
        int theChar)
        {
         return -1;
        }
    @Override
      public boolean exit(
        int exitCode)
        {     
         return true; 
        }  
    
}
