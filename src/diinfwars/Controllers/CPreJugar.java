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
    public CPreJugar(CTorneo parent,Batalla data,int oroi,int mapa){
        pT = parent;
        String j1=data.getJugador1().getNombre(),j2=data.getJugador2().getNombre();
        boolean cpu1=data.getJugador1().isCpu(),cpu2=data.getJugador2().isCpu();
        int orok=data.getOroKiosco();
        
        v.setTorneo(j1,cpu1,j2,cpu2,orok,oroi,mapa);
    }

    public void run(){
        if (this.v == null){this.v = new VPreJugar(this);}
        this.v.setVisible(true);
        //Seteada de botones como corresponde
        this.v.getCMasJ1().setEnabled(true);
        this.v.getMMasJ1().setEnabled(true);
        this.v.getCMasJ2().setEnabled(true);
        this.v.getMMasJ2().setEnabled(true);
        this.v.getCMenosJ1().setEnabled(false);
        this.v.getMMenosJ1().setEnabled(false);
        this.v.getCMenosJ2().setEnabled(false);
        this.v.getMMenosJ2().setEnabled(false);
        this.v.getPuntosCJ1().setText("0");
        this.v.getPuntosCJ2().setText("0");
        this.v.getPuntosMJ1().setText("0");
        this.v.getPuntosMJ2().setText("0");
        this.v.getPuntosJ1().setText("30");
        this.v.getPuntosJ2().setText("30");
    }
    
             
    public void comenzarPartida(int valorMapa, int oroInicio, int oroInicioKiosco){            
        //Obtener nombres
        String j1 = v.getNombre1().getText();
        String j2 = v.getNombre2().getText();
        //Obtener valores de ataques Corto y Medio para Unidad Profesor
        int cortoJ1 = Integer.parseInt(v.getPuntosCJ1().getText());
        int medioJ1 = Integer.parseInt(v.getPuntosMJ1().getText());
        int cortoJ2 = Integer.parseInt(v.getPuntosCJ2().getText());
        int medioJ2 = Integer.parseInt(v.getPuntosMJ2().getText());
        //Obtener Naturalezas de Unidad Profesor
        String nat1J1 = null;
        String nat2J1 = null;
        String nat1J2 = null;
        String nat2J2 = null;
        switch(v.getNaturaleza1J1().getSelectedIndex()){
            case 0: nat1J1 = "Deportista";break;
            case 1: nat1J1 = "Estudioso";break;
            case 2: nat1J1 = "Incoherente";break;
            case 3: nat1J1 = "Normal";break;
        }
        switch(v.getNaturaleza2J1().getSelectedIndex()){
            case 0: nat2J1 = "Deportista";break;
            case 1: nat2J1 = "Estudioso";break;
            case 2: nat2J1 = "Incoherente";break;
            case 3: nat2J1 = "Normal";break;
        }
        switch(v.getNaturaleza1J2().getSelectedIndex()){
            case 0: nat1J2 = "Deportista";break;
            case 1: nat1J2 = "Estudioso";break;
            case 2: nat1J2 = "Incoherente";break;
            case 3: nat1J2 = "Normal";break;
        }
        switch(v.getNaturaleza2J2().getSelectedIndex()){
            case 0: nat2J2 = "Deportista";break;
            case 1: nat2J2 = "Estudioso";break;
            case 2: nat2J2 = "Incoherente";break;
            case 3: nat2J2 = "Normal";break;
        }
        
        
        // Se prepara el objeto Batalla
        Jugador jug1=new Jugador(j1,true), jug2 = new Jugador(j2,true);
        Mapa mapa1 = new Mapa(valorMapa);

        // estos datos se sacan de login, preJugar y configuracion profesor
        Batalla datosBatalla = new Batalla(mapa1,oroInicioKiosco,jug1,oroInicio,1,cortoJ1,medioJ1,nat1J1,nat2J1,jug2,oroInicio,1,cortoJ2,medioJ2,nat1J2,nat2J2);

        cEnfrentamiento = new CEnfrentamiento(this, datosBatalla);
        v.setVisible(false);
        cEnfrentamiento.run();
    }
    //Verifica que esten bien dados los puntos
    public void verificar(int puntosRestantes, int puntosC, int puntosM){

        if(puntosRestantes >= 30 ){
            v.getCMenosJ1().setEnabled(false);
            v.getMMenosJ1().setEnabled(false);
        }
        if(puntosRestantes <= 0){
            v.getCMasJ1().setEnabled(false);
            v.getMMasJ1().setEnabled(false);
        }
  
        if (puntosRestantes<30){
            v.getCMenosJ1().setEnabled(true);
            v.getCMasJ1().setEnabled(true);
            v.getMMenosJ1().setEnabled(true);
            v.getMMasJ1().setEnabled(true);
            if(puntosC == 0){v.getCMenosJ1().setEnabled(false);}
            if(puntosM == 0){v.getMMenosJ1().setEnabled(false);}
            if(puntosRestantes<2){v.getCMasJ1().setEnabled(false);}
            if(puntosRestantes<3){v.getMMasJ1().setEnabled(false);}
            if (puntosRestantes <= 0){
                if (puntosC <=15){
                    v.getCMasJ1().setEnabled(false);}
                if (puntosM <=10){
                    v.getMMasJ1().setEnabled(false);}
            }
        }     
    }
    public void verificar2(int puntosRestantes, int puntosC, int puntosM){

        if(puntosRestantes >= 30 ){
            v.getCMenosJ2().setEnabled(false);
            v.getMMenosJ2().setEnabled(false);
        }
        if(puntosRestantes <= 0){
            v.getCMasJ2().setEnabled(false);
            v.getMMasJ2().setEnabled(false);
        }
        if (puntosRestantes<30){
            v.getCMenosJ2().setEnabled(true);
            v.getCMasJ2().setEnabled(true);
            v.getMMenosJ2().setEnabled(true);
            v.getMMasJ2().setEnabled(true);
            if(puntosC == 0){v.getCMenosJ2().setEnabled(false);}
            if(puntosM == 0){v.getMMenosJ2().setEnabled(false);}
            if(puntosRestantes<2){v.getCMasJ2().setEnabled(false);}
            if(puntosRestantes<3){v.getMMasJ2().setEnabled(false);}
            if (puntosRestantes <= 0){
                if (puntosC <=15){
                    v.getCMasJ2().setEnabled(false);}
                if (puntosM <=10){
                    v.getMMasJ2().setEnabled(false);}
            }
        }     
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
//        verificar();
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
            
            //Puntos para ataques de Profesor
            //Jugador 1
            //Añadir puntos de ataque a rango Corto
            int puntosRestantes = Integer.parseInt(v.getPuntosJ1().getText());
            if(boton == v.getCMasJ1()){
                int puntosC = Integer.parseInt(v.getPuntosCJ1().getText());
                int puntosM = Integer.parseInt(v.getPuntosMJ1().getText());
                puntosRestantes = puntosRestantes - 2;
                puntosC = puntosC + 1;
                v.getPuntosJ1().setText(Integer.toString(puntosRestantes));
                v.getPuntosCJ1().setText(Integer.toString(puntosC));
                verificar(puntosRestantes, puntosC, puntosM);
            }
            //Quitar puntos de ataque a rango Corto
            if(boton == v.getCMenosJ1()){
                int puntosC = Integer.parseInt(v.getPuntosCJ1().getText());
                int puntosM = Integer.parseInt(v.getPuntosMJ1().getText());
                puntosRestantes = puntosRestantes + 2;
                puntosC = puntosC - 1;
                v.getPuntosJ1().setText(Integer.toString(puntosRestantes));
                v.getPuntosCJ1().setText(Integer.toString(puntosC));
                verificar(puntosRestantes, puntosC, puntosM);
            }
            //Añadir puntos de ataque a rango Medio
            if(boton == v.getMMasJ1()){
                int puntosC = Integer.parseInt(v.getPuntosCJ1().getText());
                int puntosM = Integer.parseInt(v.getPuntosMJ1().getText());
                puntosRestantes = puntosRestantes - 3;
                puntosM = puntosM + 1;
                v.getPuntosJ1().setText(Integer.toString(puntosRestantes));
                v.getPuntosMJ1().setText(Integer.toString(puntosM));
                verificar(puntosRestantes, puntosC, puntosM);
            }
            //Quitar puntos de ataque a rango Medio
            if(boton == v.getMMenosJ1()){
                int puntosC = Integer.parseInt(v.getPuntosCJ1().getText());
                int puntosM = Integer.parseInt(v.getPuntosMJ1().getText());
                puntosRestantes = puntosRestantes + 3;
                puntosM = puntosM - 1;
                v.getPuntosJ1().setText(Integer.toString(puntosRestantes));
                v.getPuntosMJ1().setText(Integer.toString(puntosM));
                verificar(puntosRestantes, puntosC, puntosM);
            }
            //Jugador 2
            //Añadir puntos de ataque a rango Corto
            int puntosRestantes2 = Integer.parseInt(v.getPuntosJ2().getText());
            if(boton == v.getCMasJ2()){
                int puntosC = Integer.parseInt(v.getPuntosCJ2().getText());
                int puntosM = Integer.parseInt(v.getPuntosMJ2().getText());
                puntosRestantes2 = puntosRestantes2 - 2;
                puntosC = puntosC + 1;
                v.getPuntosJ2().setText(Integer.toString(puntosRestantes2));
                v.getPuntosCJ2().setText(Integer.toString(puntosC));
                verificar2(puntosRestantes2, puntosC, puntosM);
            }
            //Quitar puntos de ataque a rango Corto
            if(boton == v.getCMenosJ2()){
                int puntosC = Integer.parseInt(v.getPuntosCJ2().getText());
                int puntosM = Integer.parseInt(v.getPuntosMJ2().getText());
                puntosRestantes2 = puntosRestantes2 + 2;
                puntosC = puntosC - 1;
                v.getPuntosJ2().setText(Integer.toString(puntosRestantes2));
                v.getPuntosCJ2().setText(Integer.toString(puntosC));
                verificar2(puntosRestantes2, puntosC, puntosM);
            }
            //Añadir puntos de ataque a rango Medio
            if(boton == v.getMMasJ2()){
                int puntosC = Integer.parseInt(v.getPuntosCJ2().getText());
                int puntosM = Integer.parseInt(v.getPuntosMJ2().getText());
                puntosRestantes2 = puntosRestantes2 - 3;
                puntosM = puntosM + 1;
                v.getPuntosJ2().setText(Integer.toString(puntosRestantes2));
                v.getPuntosMJ2().setText(Integer.toString(puntosM));
                verificar2(puntosRestantes2, puntosC, puntosM);
            }
            //Quitar puntos de ataque a rango Medio
            if(boton == v.getMMenosJ2()){
                int puntosC = Integer.parseInt(v.getPuntosCJ2().getText());
                int puntosM = Integer.parseInt(v.getPuntosMJ2().getText());
                puntosRestantes2 = puntosRestantes2 + 3;
                puntosM = puntosM - 1;
                v.getPuntosJ2().setText(Integer.toString(puntosRestantes2));
                v.getPuntosMJ2().setText(Integer.toString(puntosM));
                verificar2(puntosRestantes2, puntosC, puntosM);
            }
            
        }
    }    
}
