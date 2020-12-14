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
class CaptureRouter implements Router{
    
    javax.swing.JTextArea recomendacionPanel;
    
    public CaptureRouter(javax.swing.JTextArea recomendacionPanel){
        super();
        this.recomendacionPanel = recomendacionPanel;
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
          this.recomendacionPanel.setText(printString);
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
