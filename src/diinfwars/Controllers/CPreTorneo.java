/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diinfwars.Controllers;

import diinfwars.Models.Jugador;
import diinfwars.Views.VPreTorneo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 *
 * @author MonodetH
 */
public class CPreTorneo implements ActionListener{
    private CPrincipal p;
    
    /**Instancia de la vista principal*/
    private static VPreTorneo v;
    
    private CTorneo cTorneo;
    
    public CPreTorneo(CPrincipal padre){
        p = padre;
    }
    
    
    public void run(){
        if (this.v == null){this.v = new VPreTorneo(this);}
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
            if (boton == v.getBComenzar()){
                Jugador[] jugadores = new Jugador[8];
                jugadores[0]=(v.getCheckCpu1())?new Jugador("Cpu",true):new Jugador(v.getJugador1());
                jugadores[1]=(v.getCheckCpu2())?new Jugador("Cpu",true):new Jugador(v.getJugador2());
                jugadores[2]=(v.getCheckCpu3())?new Jugador("Cpu",true):new Jugador(v.getJugador3());
                jugadores[3]=(v.getCheckCpu4())?new Jugador("Cpu",true):new Jugador(v.getJugador4());
                jugadores[4]=(v.getCheckCpu5())?new Jugador("Cpu",true):new Jugador(v.getJugador5());
                jugadores[5]=(v.getCheckCpu6())?new Jugador("Cpu",true):new Jugador(v.getJugador6());
                jugadores[6]=(v.getCheckCpu7())?new Jugador("Cpu",true):new Jugador(v.getJugador7());
                jugadores[7]=(v.getCheckCpu8())?new Jugador("Cpu",true):new Jugador(v.getJugador8());
                
                
                
                cTorneo = new CTorneo(this,jugadores,v.getOroi(),v.getOrok(),v.getMapa());
                v.setVisible(false);
                cTorneo.run();
                
            }
        }
    }
}
