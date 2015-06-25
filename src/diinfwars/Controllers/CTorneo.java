/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diinfwars.Controllers;

import diinfwars.Views.VTorneo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author MonodetH
 */
class CTorneo implements ActionListener{
    private CPrincipal p;
    
    /**Instancia de la vista principal*/
    private VTorneo vi;
    
    public CTorneo(CPrincipal padre){
        p = padre;
    }
    
    public void run(){
        if (this.vi == null){this.vi = new VTorneo(this);}
        this.vi.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        vi.setVisible(false);
        p.run();
    }
}
