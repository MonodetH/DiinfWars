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
        if (pred == 1){
            for(int i = 0;i<9;i++){
                for(int j = 0;j<20;j++){
                    capaCasillas[i][j] = new Pastos();
                }
            }
        }
    }
    
    public boolean moverUnidad(int filaInicial, int colInicial,int filaFinal,int colFinal){
        
        return true;
    }
    
    public Casilla[][] getCasillas(){
        return this.capaCasillas;
    }
}
