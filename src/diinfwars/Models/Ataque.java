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
    
    @Override
    public String toString(){
        String sRango = "Corto";
        if (rango == 1){sRango = "Corto";}
        else if (rango == 2){sRango = "Medio";}
        else if (rango == 3){sRango = "Largo";}
        return (sRango+" - "+String.valueOf(dano)+" - "+String.valueOf(cantidadGolpes));
    }
    public int[] toInt(){
        int[] retorno = {rango,dano,cantidadGolpes};
        return retorno;
    }
    
}
