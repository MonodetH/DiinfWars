/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diinfwars.Controllers;

import diinfwars.Views.VCreditos;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 *
 * @author MonodetH
 */
public class CCreditos implements ActionListener{
    private CPrincipal p;
    
    /**Instancia de la vista principal*/
    private static VCreditos v;
    
    public CCreditos(CPrincipal padre){
        p = padre;
    }
    
    public void run(){
        if (this.v == null){this.v = new VCreditos(this);}
        this.v.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        // En caso de que sean botones
        if( source instanceof JButton){
            JButton boton = (JButton) e.getSource();
            if (boton == v.getBVolver()){
                v.dispose();
                p.run();
            }
        }
    }
}
