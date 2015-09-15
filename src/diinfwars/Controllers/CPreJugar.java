/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diinfwars.Controllers;

import diinfwars.Views.VPreJugar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 *
 * @author MonodetH
 */
public class CPreJugar implements ActionListener{
    /**Controlador padre*/
    private CPrincipal p;
    /**Vista*/
    private VPreJugar v;
    
    private CEnfrentamiento cEnfrentamiento;
    
    public CPreJugar(CPrincipal parent){
        p = parent;
    }

    public void run(){
        if (this.v == null){
            this.v = new VPreJugar(this);}
        this.v.setVisible(true);
    }
       
    public void comenzarPartida(int valorMapa){            
        String j1 = v.getNombre1().getText();
        String j2 = v.getNombre2().getText();
        cEnfrentamiento = new CEnfrentamiento(this, valorMapa, j1, j2);
        v.setVisible(false);
        cEnfrentamiento.run();
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
            }else if (boton == v.getBJugar()){
                int valorMapa =(int)v.getboxTipoMapa().getSelectedIndex();
                comenzarPartida(valorMapa);           
            }
        }
    }
}
