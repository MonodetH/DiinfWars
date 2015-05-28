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
public class Mapa {
    // Atributos
    private Casilla[][] capaCasillas = new Casilla[9][20];
    private boolean[][] capaRango = new boolean[9][20];
    
    /**
     * Constructor de mapa
     * @param pred mapa seleccionado: 0 = Aleatorio, 1 = Predet1, 2 = Predet2, 3 = Predet3 
     */
    public Mapa(int pred){
        
    }
}
