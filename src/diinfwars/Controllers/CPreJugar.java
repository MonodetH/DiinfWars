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
        //Obtener nombres
        String j1 = v.getNombre1().getText();
        String j2 = v.getNombre2().getText();
        //Obtener valores de ataques Corto y Medio para Unidad Profesor
        int cortoJ1 = (int)v.getCortoJ1().getValue();
        int medioJ1 = (int)v.getMedioJ1().getValue();
        int cortoJ2 = (int)v.getCortoJ2().getValue();
        int medioJ2 = (int)v.getMedioJ2().getValue();
        //Obtener Naturalezas de Unidad Profesor
        String nat1J1 = null;
        String nat2J1 = null;
        String nat1J2 = null;
        String nat2J2 = null;
        switch(v.getNaturaleza1J1().getSelectedIndex()){
            case 0: nat1J1 = "Carretero";break;
            case 1: nat1J1 = "Deportista";break;
            case 2: nat1J1 = "Deprimido";break;
            case 3: nat1J1 = "Estudioso";break;
            case 4: nat1J1 = "Incoherente";break;
            case 5: nat1J1 = "Normal";break;
            case 6: nat1J1 = "Tortuga";break;    
        }
        switch(v.getNaturaleza2J1().getSelectedIndex()){
            case 0: nat2J1 = "Carretero";break;
            case 1: nat2J1 = "Deportista";break;
            case 2: nat2J1 = "Deprimido";break;
            case 3: nat2J1 = "Estudioso";break;
            case 4: nat2J1 = "Incoherente";break;
            case 5: nat2J1 = "Normal";break;
            case 6: nat2J1 = "Tortuga";break;    
        }
        switch(v.getNaturaleza1J2().getSelectedIndex()){
            case 0: nat1J2 = "Carretero";break;
            case 1: nat1J2 = "Deportista";break;
            case 2: nat1J2 = "Deprimido";break;
            case 3: nat1J2 = "Estudioso";break;
            case 4: nat1J2 = "Incoherente";break;
            case 5: nat1J2 = "Normal";break;
            case 6: nat1J2 = "Tortuga";break;    
        }
        switch(v.getNaturaleza2J2().getSelectedIndex()){
            case 0: nat2J2 = "Carretero";break;
            case 1: nat2J2 = "Deportista";break;
            case 2: nat2J2 = "Deprimido";break;
            case 3: nat2J2 = "Estudioso";break;
            case 4: nat2J2 = "Incoherente";break;
            case 5: nat2J2 = "Normal";break;
            case 6: nat2J2 = "Tortuga";break;    
        }
        
        
        // Se prepara el objeto Batalla
        Jugador jug1=new Jugador(j1), jug2 = new Jugador(j2);
        Mapa mapa1 = new Mapa(valorMapa);

        // estos datos se sacan de login, preJugar y configuracion profesor
        Batalla datosBatalla = new Batalla(mapa1,oroInicioKiosco,jug1,oroInicio,1,cortoJ1,medioJ1,nat1J1,nat2J1,jug2,oroInicio,1,cortoJ2,medioJ2,nat1J2,nat2J2);

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
