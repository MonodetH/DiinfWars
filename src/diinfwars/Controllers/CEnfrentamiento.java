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
import javax.swing.JToggleButton;

/**
 * Controlador de la vista enfrentamiento
 * @author MonodetH
 */
public class CEnfrentamiento implements ActionListener,MouseListener{
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
    
    
    
    /**Constructor que instancia la vista*/
    public CEnfrentamiento(){
        /*Esto se hace en CPreEnfrentamiento*/
        Jugador jug1=new Jugador("Mono"), jug2 = new Jugador("Ale");
        Mapa mapa1 = new Mapa(2);
        /*El objeto Batalla deberia ser pasado al constructor por CPreEnfrentamiento*/
        Batalla datosBatalla = new Batalla(mapa1,5,jug1,10,1,2,3,"Estudioso","Deportista",jug2,10,1,3,2,"Estudioso","Deportista");
        
        
        
        
        /*Aqui empieza este controlador*/
        this.batalla = datosBatalla;
        this.mapa = batalla.getMapa();
        this.jugador1 = batalla.getJugador1();
        this.jugador2 = batalla.getJugador2();
        this.estratega1 = batalla.getEstratega1();
        this.estratega2 = batalla.getEstratega2();
        this.run();
        v.setLabel2(jugador1.getNombre());
        v.dibujarTerreno(mapa.terrenoToString());
        v.dibujarUnidades(mapa.unidadesToString());
        
    }
    
    public void run(){
        if (this.v == null){this.v = new VEnfrentamiento(this,this);}
        this.v.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        
        // En caso de que sean botones
        if( source instanceof JButton){
            JButton boton = (JButton) e.getSource();
            // finalizar turno o rendirse
            if(boton.getText() == "Finalizar Turno"){
                String nombreJugador = (v.getJugador()==1)?jugador2.getNombre():jugador1.getNombre();
                v.toggleJugador(nombreJugador);
                v.setModo(0);
                v.dibujarRango(new boolean[9][20]);
                
                // se reinician las listas de movidos y atacantes
                uMovidas.clear();
                uAtacantes.clear();
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
                    v.dibujarUnidades(mapa.unidadesToString());
                    boolean[][] rango = mapa.getRango(v.getModo(),pos[0],pos[1],v.getJugador(),1);
                    v.dibujarRango(rango);
                }
            }
            
        // En caso de que sean botones toggle (los modos del tablero)
        }else if(source instanceof JToggleButton){
            JToggleButton boton = (JToggleButton) e.getSource();
            // cambio de modo
            if(boton.getText() == "Mover"){
                v.setModo(1);
                int[] casillaActual = v.getCasillaSeleccionada();
                boolean[][] rango = mapa.getRango(v.getModo(),casillaActual[0],casillaActual[1],v.getJugador(),1);
                v.dibujarRango(rango);
            }
            else if(boton.getText() == "Atacar"){
                v.setModo(2);
                int[] casillaActual = v.getCasillaSeleccionada();
                boolean[][] rango = mapa.getRango(v.getModo(),casillaActual[0],casillaActual[1],v.getJugador(),2);
                v.dibujarRango(rango);
            }
            else if(boton.getText() == "As Táctico"){
                v.setModo(3);
                int[] casillaActual = v.getCasillaSeleccionada();
                boolean[][] rango = mapa.getRango(v.getModo(),casillaActual[0],casillaActual[1],v.getJugador(),1);
                v.dibujarRango(rango);
            }
            else if(boton.getText() == "Reclutar"){
                v.setModo(4);
                int[] casillaActual = v.getCasillaSeleccionada();
                boolean[][] rango = mapa.getRango(v.getModo(),casillaActual[0],casillaActual[1],v.getJugador(),1);
                v.dibujarRango(rango);
                int oroJugador = (v.getJugador() == 1)?estratega1.getOro():estratega1.getOro();
                v.mostrarMenuReclutar(oroJugador);
            }
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
        
        if(v.getModo() == 1){ // modo mover
            int[] posInicial = v.getCasillaSeleccionada();
            Unidad uSeleccionada = mapa.getUnidad(posInicial[0], posInicial[1]);
            if(uSeleccionada != null && v.enRango(i, j) && v.getJugador() == uSeleccionada.getEquipo() && !uMovidas.contains(uSeleccionada)){
                if (mapa.moverUnidad(posInicial[0], posInicial[1], i, j)){
                    uMovidas.add(uSeleccionada);
                }
                v.dibujarUnidades(mapa.unidadesToString());
            }
        }else if(v.getModo() == 2){ // modo atacar
            int[] posInicial = v.getCasillaSeleccionada();
            Unidad uAtacante = mapa.getUnidad(posInicial[0], posInicial[1]);
            Unidad uDefensora = mapa.getUnidad(i,j);
            if(uAtacante != null && v.enRango(i, j) && v.getJugador() == uAtacante.getEquipo() ){
                
            }
        }else if(v.getModo() == 3){ // modo as tactico
            // aqui depende de cada as
        }else if(v.getModo() == 4){ // modo reclutar
            // CREO QUE NO SE HACE NADA AQUI
        }
        
        // Actualizar Casilla seleccionada y matriz de rango
        this.v.setCasillaSeleccionada(i,j);
        v.dibujarRango(mapa.getRango(v.getModo(), i, j,v.getJugador(),1));
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
        new CEnfrentamiento();
    }
}
