/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diinfwars.Controllers;

import diinfwars.Views.VPreJugar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author MonodetH
 */
public class CPreJugar implements ActionListener{
    /**Controlador padre*/
    private CPrincipal p;
    /**Vista*/
    private VPreJugar v;
    
    public CPreJugar(CPrincipal parent){
        p = parent;
    }

    public void run(){
        if (this.v == null){this.v = new VPreJugar(this);}
        this.v.setVisible(true);
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
}
