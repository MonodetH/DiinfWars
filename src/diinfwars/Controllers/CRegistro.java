/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diinfwars.Controllers;

import diinfwars.Views.VRegistro;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 *
 * @author MonodetH
 */
public class CRegistro implements ActionListener{
    private CPrincipal parentPrincipal = null;
    private CLogin parentLogin = null;
    
    private static VRegistro v;
    
    public CRegistro(){}
    public CRegistro(CPrincipal parent){
        parentPrincipal = parent;
    }
    public CRegistro(CLogin parent){
        parentLogin = parent;
    }
    
    public void run(){
        if (this.v == null){this.v = new VRegistro(this);}
        this.v.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        //Botones
        if( source instanceof JButton){
            JButton boton = (JButton) e.getSource();
            if (boton.getText() == "Volver"){
                v.setVisible(false);
                parentLogin.run();
            }
        
//        v.setVisible(false);
//        p.run();
        
    }
    
}
}