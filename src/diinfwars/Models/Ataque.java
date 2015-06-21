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
public class Ataque {
    // ATRIBUTOS
    private int dano;
    /** 1 = Corto; 2 = Medio; 3 = Largo*/
    private int rango;
    private int cantidadGolpes;
    
    /**
     * Constructor Ataque
     * @param dano Daño base
     * @param cantidadGolpes Cantidad de golpes 
     * @param rango Rango de ataque: 1 = Corto; 2 = Medio; 3 = Largo
     */
    public Ataque(int dano, int cantidadGolpes, int rango){
        this.dano = dano;
        this.rango = rango;
        this.cantidadGolpes = cantidadGolpes;
    }
    
    public int getDano(){return dano;}
    public int getRango(){return rango;}
    public int getGolpes(){return cantidadGolpes;}
    
}
