/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diinfwars.Controllers;

import diinfwars.Models.Batalla;
import diinfwars.Models.Estratega;
import diinfwars.Models.Jugador;
import diinfwars.Models.Mapa;
import diinfwars.Models.Unidad;
import diinfwars.Views.VEnfrentamiento;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JToggleButton;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * Controlador de la vista enfrentamiento
 * @author MonodetH
 */
public class CEnfrentamiento implements ActionListener,MouseListener,ListSelectionListener{
    /**Controlador padre*/
    private CPreJugar p;
    /**Instancia de la vista asociada*/
    private VEnfrentamiento v;
    /**Jugador asociado*/
    private Jugador jugador1,jugador2;
    /**Estrategas por jugador*/
    private Estratega estratega1,estratega2;
    /**Mapa en el que se hará la batalla*/
    private Mapa mapa;
    /**Datos de la batalla*/
    private Batalla batalla;
    
    private ArrayList<Unidad> uMovidas = new ArrayList<Unidad>();
    private ArrayList<Unidad> uAtacantes = new ArrayList<Unidad>();
    
    private Unidad unidadAtacante = null;
    private Unidad unidadDefensora = null;
    
    
    
    /**Constructor que instancia la vista*/
    public CEnfrentamiento(CPreJugar padre){
        /*Esto se hace en CPreEnfrentamiento*/
        Jugador jug1=new Jugador("Gerardo"), jug2 = new Jugador("Alejandro");
        Mapa mapa1 = new Mapa(2);
        /*El objeto Batalla deberia ser pasado al constructor por CPreEnfrentamiento*/
        Batalla datosBatalla = new Batalla(mapa1,5,jug1,50,1,2,3,"Estudioso","Deportista",jug2,50,1,3,2,"Estudioso","Deportista");

        /*Aqui empieza este controlador*/
        this.p = padre;
        this.batalla = datosBatalla;
        this.mapa = batalla.getMapa();
        this.jugador1 = batalla.getJugador1();
        this.jugador2 = batalla.getJugador2();
        this.estratega1 = batalla.getEstratega1();
        this.estratega2 = batalla.getEstratega2();
    }
    /**Constructor que instancia la vista*/
    public CEnfrentamiento(CPreJugar padre,Batalla datosBatalla){
        this.batalla = datosBatalla;
        this.mapa = batalla.getMapa();
        this.jugador1 = batalla.getJugador1();
        this.jugador2 = batalla.getJugador2();
        this.estratega1 = batalla.getEstratega1();
        this.estratega2 = batalla.getEstratega2();
    }
    
    public void run(){
        if (this.v == null){
            this.v = new VEnfrentamiento(this,this,this);
            v.setLabel2(jugador1.getNombre());
            v.dibujarTerreno(mapa.terrenoToString());
            v.dibujarUnidades(mapa.unidadesToString());
        }
        this.v.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        
        // En caso de que sean botones
        if( source instanceof JButton){
            JButton boton = (JButton) e.getSource();
            if(boton.getText() == "Rendirse"){
                v.setVisible(false);
                p.run();
                }
            // finalizar turno o rendirse
            if(boton.getText() == "Finalizar Turno"){
                String nombreJugador = (v.getJugador()==1)?jugador2.getNombre():jugador1.getNombre();
                v.toggleJugador(nombreJugador);
                v.setModo(0);
                v.dibujarRango(new boolean[9][20]);
                
                //Actualiza los paneles de informacion
                Estratega estratega = (v.getJugador() == 1)?estratega1:estratega2;
                v.actualizarPanelReclutar(estratega.getOro());
                estratega.cobrarMantencion();
                //v.actualizarPanelDefault(estratega.cobrarMantencion());
                
                // se reinician las listas de movidos y atacantes
                uMovidas.clear();
                uAtacantes.clear();
                this.unidadAtacante = null;
                this.unidadDefensora = null;
            }
            
            
            if(boton.getParent() == v.getPanelReclutar()){
                String tipoUnidad = "Cachorro";
                if(boton == v.getReclutarCachorro()){tipoUnidad = "Cachorro";}
                else if(boton == v.getReclutarAlumno()){tipoUnidad = "Alumno";}
                else if(boton == v.getReclutarSuperior()){tipoUnidad = "AlumnoNivelSuperior";}
                else if(boton == v.getReclutarAyudante()){tipoUnidad = "Ayudante";}
                else if(boton == v.getReclutarCoordinador()){tipoUnidad = "CoordinadorAyudantes";}
                else if(boton == v.getReclutarPame()){tipoUnidad = "Pame";}
                
                int[] pos = v.getCasillaSeleccionada();
                if(v.getModo() == 4 && v.enRango(pos[0], pos[1])){
                    Estratega estratega = (v.getJugador() == 1)?estratega1:estratega2;
                    Unidad unidad = estratega.reclutar(tipoUnidad);
                    mapa.ubicarUnidad(unidad,pos[0], pos[1]);
                    uMovidas.add(unidad);
                    uAtacantes.add(unidad);
                    
                    boolean[][] rango = mapa.getRango(v.getModo(),pos[0],pos[1],v.getJugador(),v.getRangoAtaque());
                    v.dibujarRango(rango);
                    v.dibujarUnidades(mapa.unidadesToString());
                    v.actualizarPanelReclutar(estratega.getOro());
                }
            }
            
        // En caso de que sean botones toggle (los modos del tablero)
        }else if(source instanceof JToggleButton){
            JToggleButton boton = (JToggleButton) e.getSource();
            
            // Dejar en estado neutro
            this.unidadAtacante = null;
            this.unidadDefensora = null;
            this.v.setEnableAtacar(false);
            
            // cambio de modo
            if(boton.getText() == "Mover"){
                v.setModo(1);
            }
            else if(boton.getText() == "Atacar"){
                v.setModo(2);
                int[] pos = v.getCasillaSeleccionada();
                Unidad unidad = mapa.getUnidad(pos[0], pos[1]);
                if(unidad != null){
                    String[] listaAtaques = unidad.getListaAtaques();
                    v.setListaAtaques(listaAtaques);
                }
                
            }
            else if(boton.getText() == "As Táctico"){
                v.setModo(3);
            }
            else if(boton.getText() == "Reclutar"){
                v.setModo(4);
                Estratega estratega = (v.getJugador() == 1)?estratega1:estratega2;
                v.actualizarPanelReclutar(estratega.getOro());
                
            }
            int[] casillaActual = v.getCasillaSeleccionada();
            boolean[][] rango = mapa.getRango(v.getModo(),casillaActual[0],casillaActual[1],v.getJugador(),v.getRangoAtaque());
            v.dibujarRango(rango);
            
        // En caso de que sea una accion no implementada
        }else{
            System.out.println("Otro");
        }
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        JLabel source = (JLabel) e.getSource();
        
        // Obtener posicion seleccionada
        int i=0, j=0;
        for(int x=0;x<9;x++){
            for(int y=0;y<20;y++){
                if(source.equals(v.getMatrizUnidad()[x][y])){
                    i=x;
                    j=y;
                }
            }
        }
        
        // Obtener informacion necesaria
        int[] posAntigua = v.getCasillaSeleccionada(); //Posicion anterior a la ultima seleccion
            // i, j posicion actual
        Unidad uAntigua = mapa.getUnidad(posAntigua[0], posAntigua[1]); //unidad en la posicion antigua, si la hay
        Unidad uNueva = mapa.getUnidad(i,j); //unidad en la posicion nueva, si la hay
        
        // Procedimiento general pre analisis
        if(uNueva != null){
            if(v.getModo() != 2 && uNueva!=uAntigua){
                String[] listaAtaques = uNueva.getListaAtaques();
                v.setListaAtaques(listaAtaques);
            }
        }else{
            v.setListaAtaques(null);
        }
        
        
        // Analizar segun modo
        if(v.getModo() == 1){ // modo mover
            if(uAntigua != null && v.enRango(i, j) && v.getJugador() == uAntigua.getEquipo() && !uMovidas.contains(uAntigua)){
                if (mapa.moverUnidad(posAntigua[0], posAntigua[1], i, j)){
                    uMovidas.add(uAntigua);
                }
                v.dibujarUnidades(mapa.unidadesToString());
            }
            this.v.clearCasillaObjetivo();
            this.v.setCasillaSeleccionada(i,j);
            
            
            
        }else if(v.getModo() == 2){ // modo atacar
            this.unidadAtacante = null;
            this.unidadDefensora = null;
            this.v.setEnableAtacar(false);
            
            //Atacante y defensor valido
            if(uAntigua != null && v.getJugador() == uAntigua.getEquipo() && v.enRango(i, j)
                && uNueva != null  &&  uAntigua.getEquipo() != uNueva.getEquipo() 
                && i != v.getCasillaObjetivo()[0] && j != v.getCasillaObjetivo()[1]){
                    System.out.println("SE DEBE MOSTRAR LA INFO EN EL PANEL");
                    this.unidadAtacante = uAntigua;
                    this.unidadDefensora = uNueva;
                    
                    this.v.setEnableAtacar(true);
                    this.v.setCasillaObjetivo(i,j);
            }else{
                if(uNueva != null){
                    if(uNueva!=uAntigua){
                        String[] listaAtaques = uNueva.getListaAtaques();
                        v.setListaAtaques(listaAtaques);
                    }
                }
                this.v.clearCasillaObjetivo();
                this.v.setCasillaSeleccionada(i,j);
            }
            
            
        }else if(v.getModo() == 3){ // modo as tactico
            // aqui depende de cada as
            this.v.setCasillaSeleccionada(i,j);
        }else if(v.getModo() == 4){ // modo reclutar
            // CREO QUE NO SE HACE NADA AQUI
            this.v.setCasillaSeleccionada(i,j);
        }else{ // Modo 0
            this.v.setCasillaSeleccionada(i,j);
        }
        
        // Dibujar nuevo rango
        i = v.getCasillaSeleccionada()[0];
        j = v.getCasillaSeleccionada()[1];
        v.dibujarRango(mapa.getRango(v.getModo(), i, j,v.getJugador(),v.getRangoAtaque()));
    }
    
    @Override
    public void valueChanged(ListSelectionEvent e) {
        int[] casillaActual = v.getCasillaSeleccionada();
        boolean[][] rango = mapa.getRango(v.getModo(),casillaActual[0],casillaActual[1],v.getJugador(),v.getRangoAtaque());
        v.dibujarRango(rango);
    }

    @Override
    public void mouseClicked(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
    
    public static void main(String[] args){
        new CEnfrentamiento(null);
    }

    
}
