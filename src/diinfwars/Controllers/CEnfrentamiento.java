/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diinfwars.Controllers;

import diinfwars.Models.Batalla;
import diinfwars.Models.Jugador;
import diinfwars.Models.Mapa;
import diinfwars.Views.VEnfrentamiento;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
    /**Mapa en el que se hará la batalla*/
    private Mapa mapa;
    
    
    /**Constructor que instancia la vista*/
    public CEnfrentamiento(){
        /*Esto se hace en CPreEnfrentamiento*/
        Jugador jug1=new Jugador("Mono"), jug2 = new Jugador("Ale");
        Mapa mapa1 = new Mapa(0);
        /*El objeto Batalla deberia ser pasado al constructor por CPreEnfrentamiento*/
        Batalla batalla = new Batalla(jug1,jug2,mapa1,5,10,10);
        
        
        /*Aqui empieza este controlador*/
        
        
        this.v = new VEnfrentamiento(this,this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if( source instanceof JButton){
            // finalizar turno o rendirse
            System.out.println("Button");
        }else if(source instanceof JToggleButton){
            JToggleButton boton = (JToggleButton) e.getSource();
            // cambio de modo
            if(boton.getText() == "Mover"){v.setModo(1);}
            else if(boton.getText() == "Atacar"){v.setModo(2);}
            else if(boton.getText() == "As Táctico"){v.setModo(3);}
            else if(boton.getText() == "Reclutar"){v.setModo(4);}
        }else{
            System.out.println("Otro");
        }
        
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("Label clickeada");
        JLabel source = (JLabel) e.getSource();
        int i=0, j=0;
        for(int x=0;x<9;x++){
            for(int y=0;y<20;y++){
                if(source.equals(v.getMatrizUnidad()[x][y])){
                    i=x;
                    j=y;
                }
            }
        }
        System.out.println(String.valueOf(i)+" "+String.valueOf(j));
        this.v.setCasillaSeleccionada(i,j);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        ///throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public static void main(String[] args){
        new CEnfrentamiento();
    }
}
