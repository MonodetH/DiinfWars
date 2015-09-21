/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diinfwars.Controllers;

import diinfwars.Models.Jugador;
import diinfwars.Models.Torneo;
import diinfwars.Views.VTorneo;
import java.awt.event.ActionListener;

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
    }
    
    public void run(){
        if (this.v == null){this.v = new VTorneo(this);}
        this.v.setVisible(true);
        //Seteada de botones como corresponde
        v.setText9(datosTorneo.getJugador(9));
    }
    
    
}
