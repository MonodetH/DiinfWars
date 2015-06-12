/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diinfwars.Models;

import java.util.ArrayList;

/**
 *
 * @author MonodetH
 */
public abstract class Unidad {
    // ATRIBUTOS   
    protected int hp;
    protected int hpMax;
    protected int estamina=100;
    protected int nivel = 1;
    protected int experiencia = 0;
    protected int movimiento;
    protected int criticalMiss = 5;
    protected ArrayList<Ataque> ataques = new ArrayList<Ataque>();
    protected ArrayList<ModificadorAtributo> modificadores = new ArrayList<ModificadorAtributo>();
    protected int mantencion;
    protected int costo;
    protected String rutaSprite;
    
    
    // CONSTRUCTORES
    public Unidad(int equipo){
        setDefaults(equipo);
        // se crea una naturaleza y se agrega a modificadores
        modificadores.add(new ModificadorAtributo());
        
    }
    
    
    // METODOS ABSTRACTOS (distintos en cada unidad)
    /**
     * Setea los valores iniciales correspondiente a cada unidad
     * @param equipo El equipo al cual pertenece la unidad: 1 = equipo azul; 2 = equipo rojo
     */
    protected abstract void setDefaults(int equipo);
    
    // METODOS GENERALES (heredables)
    public String getSprite(){
        return this.rutaSprite;
    }
    
}
