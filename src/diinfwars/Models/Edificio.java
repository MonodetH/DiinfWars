/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diinfwars.Models;

/**
 *
 * @author MonodetH
 */
public class Edificio {
    public static int serial = 0; // Desde aqui se va sacando el id de los edificios
    private int id;
    private String terreno;
    private int dueno = 0;  // 0 nadie; 1-2 Equipos
    private int presencia1 = 0; // cantidad de unidades del equipo 1 situedas en el edificio
    private int presencia2 = 0;
    
    
    public Edificio(String tipo){
        this.id = ++serial;
        if(tipo=="Laboratorio"||tipo=="EAO"||tipo=="Kiosco"){
            terreno = tipo;
        }else{terreno=null;}
    }
    
    public void agregarUnidad(int equipo){
        if(equipo==1){
            presencia1++;
        }else if (equipo==2){
            presencia2++;
        }
    }
    public void quitarUnidad(int equipo){
        if(equipo==1){
            presencia1--;
        }else if (equipo==2){
            presencia2--;
        }
    }
    
    public int getDueno(){
        if(presencia1 > presencia2){
            dueno = 1;
        }else if(presencia1 < presencia2){
            dueno = 2;
        }
        return dueno;
    }
    
    public String getTipo(){return terreno;}
    public int getId(){return id;}
    
}
