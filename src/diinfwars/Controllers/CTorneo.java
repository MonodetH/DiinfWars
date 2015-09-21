/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diinfwars.Controllers;

import diinfwars.Models.Jugador;
import diinfwars.Models.Torneo;
import diinfwars.Views.VTorneo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 *
 * @author MonodetH
 */
public class CTorneo implements ActionListener{
    private CPreTorneo p;
    private CPreJugar cPreJugar;
    
    private VTorneo v;
    
    private Torneo datoTorneo;
    
    public CTorneo(CPreTorneo parent,Jugador[] jugadores, int oroi, int orok, int mapa){
        p=parent;
        datoTorneo=new Torneo(jugadores,oroi,orok,mapa);
        
        /*
        v.setText10(datoTorneo.getJugador(0));
        v.setText9(datoTorneo.getJugador(1));
        v.setText11(datoTorneo.getJugador(2));
        v.setText12(datoTorneo.getJugador(3));
        v.setText14(datoTorneo.getJugador(4));
        v.setText13(datoTorneo.getJugador(5));
        v.setText15(datoTorneo.getJugador(6));
        v.setText16(datoTorneo.getJugador(7));
        */
    }
    
    public void run(){
        if (this.v == null){this.v = new VTorneo(this);}
        this.v.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(v.getVolver()== (JButton)ae.getSource()){
            v.dispose();
            p.run();
        }
        /*
        if(v.getSiguiente()){
            
        }
        */
    }
    
    
}
