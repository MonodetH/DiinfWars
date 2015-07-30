/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diinfwars.Controllers;

import diinfwars.Views.VLogin;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 *
 * @author MonodetH
 */
public class CLogin implements ActionListener{
    private CPreTorneo parentTorneo = null;
    private CPreJugar parentJugar = null;
    
    private ArrayList<Integer> listaIdLogeados = new ArrayList<Integer>();
    
    
    /**Instancia de la vista principal*/
    private static VLogin v;
    
    public CLogin(){
    }
    public CLogin(CPreTorneo parent){
        parentTorneo = parent;
    }
    public CLogin(CPreJugar parent){
        parentJugar = parent;
    }
    
    public void logearJugador(int id){
        listaIdLogeados.add(id);
    }
    
    public void setParent(CPreTorneo parent){parentTorneo = parent;}
    public void setParent(CPreJugar parent){parentJugar = parent;}
    
    public void run(){
        if (this.v == null){this.v = new VLogin(this);}
        this.v.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        /*
        v.setVisible(false);
        p.run();
        */
    }
}
