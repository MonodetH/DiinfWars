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
    // Atributos
    private int dano;
    /**1 = Corto ;2 = Medio; 3 = Largo*/
    private int rango;
    private int cantidadGolpes;
    
    /**
     * Constructor Ataque
     * @param dano daño base
     * @param cantidadGolpes Cantidad de golpes 
     * @param rango rango de ataque: 1 = Corto ;2 = Medio; 3 = Largo
     */
    public Ataque(int dano,int cantidadGolpes,int rango){
        this.dano = dano;
        this.rango = rango;
        this.cantidadGolpes = cantidadGolpes;
    }
    
    public int getDano(){return dano;}
    public int getRango(){return rango;}
    public int getGolpes(){return cantidadGolpes;}
    
}
