/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diinfwars.Models;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author MonodetH
 */
public abstract class Unidad {
    // ATRIBUTOS   
    protected int equipo;
    protected int hp;
    protected int hpMax;
    protected int estamina = 100;
    protected int nivel = 1;
    protected int experiencia = 0;
    protected int movimiento;
    protected int criticalMiss = 5;
    protected ArrayList<Ataque> ataques = new ArrayList<Ataque>();
    protected ArrayList<ModificadorAtributo> modificadores = new ArrayList<ModificadorAtributo>();
    protected int mantencion;
    protected int costo;
    protected boolean isDead = false;
    protected String rutaSprite1;
    protected String rutaSprite2;
    protected String rutaSpriteMuerto1 = "/images/Tumba1.png";
    protected String rutaSpriteMuerto2 = "/images/Tumba2.png";
    
    
    // CONSTRUCTORES
    public Unidad(){}
    public Unidad(int equipo){
        setDefaults(equipo);
        // Se crea una naturaleza y se agrega a modificadores
        modificadores.add(new ModificadorAtributo());
    }
    
    /**Constructor de unidad, Usada solo por profesor.*/
    public Unidad(int equipo, int puntosCorto, int puntosMedio, String naturaleza1, String naturaleza2){}
    
    
    // METODOS ABSTRACTOS (Distintos en cada unidad)
    /**
     * Setea los valores iniciales correspondiente a cada unidad
     * @param equipo El equipo al cual pertenece la unidad: 1 = equipo azul; 2 = equipo rojo
     */
    protected abstract void setDefaults(int equipo);
    
    // METODOS GENERALES (heredables)
    public boolean tieneRango(int rango){
        Iterator<Ataque> iterador = ataques.iterator();
        while(iterador.hasNext()){
            if(iterador.next().getRango() == rango){
                return true;
            }
        }
        return false;
    }
    
    public String getSprite(){
        if(!isDead){
            if (equipo == 1){
                return this.rutaSprite1;
            }
            return this.rutaSprite2;
        }else{
            if (equipo == 1){
                return this.rutaSpriteMuerto1;
            }
            return this.rutaSpriteMuerto2;
        }
        
    }

    public int getEquipo() {
        return this.equipo;
    }
    
    public int getMovimientos(){
        return this.movimiento;
    }
    
    
}
