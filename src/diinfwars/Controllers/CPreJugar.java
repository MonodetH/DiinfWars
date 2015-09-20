/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diinfwars.Controllers;

import diinfwars.Models.Batalla;
import diinfwars.Models.Jugador;
import diinfwars.Models.Mapa;
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
       
    public void comenzarPartida(int valorMapa, int oroInicio, int oroInicioKiosco){            
        String j1 = v.getNombre1().getText();
        String j2 = v.getNombre2().getText();
        
        // Se prepara el objeto Batalla
        Jugador jug1=new Jugador(j1), jug2 = new Jugador(j2,true);
        Mapa mapa1 = new Mapa(valorMapa);

        // estos datos se sacan de login, preJugar y configuracion profesor
        Batalla datosBatalla = new Batalla(mapa1,oroInicioKiosco,jug1,oroInicio,1,2,3,"Estudioso","Deportista",jug2,oroInicio,1,3,2,"Estudioso","Deportista");

        cEnfrentamiento = new CEnfrentamiento(this, datosBatalla);
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
                int oroInicio =(int)v.getOroInicio().getValue();
                int oroInicioKisco = (int)v.getOroKiosco().getValue();
                comenzarPartida(valorMapa, oroInicio, oroInicioKisco);           
            }
        }
    }
}
