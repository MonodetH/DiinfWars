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
    public String[][] terrenoToString(){
        String[][] retorno = new String[9][20];
        for(int i=0;i<9;i++){
            for(int j=0;j<20;j++){
                retorno[i][j] = capaCasillas[i][j].getSprite();
            }
        }
        return retorno;
    }
    public String[][] unidadesToString(){
        String[][] retorno = new String[9][20];
        for(int i=0;i<9;i++){
            for(int j=0;j<20;j++){
                retorno[i][j] = capaCasillas[i][j].getUnidad().getSprite();
            }
        }
        return retorno;
    }
    
    public boolean[][] getRango(int modo,int fila,int col){
        if(modo == 0){return new boolean[9][20];} //esto dejalo asi
        
        //para esto recomiendo usar un metodo privado por modo, para no mezclar todo el codigo
        boolean[][] retorno = new boolean[9][20];
        retorno[1][1]=true;
        retorno[1][3]=true;
        retorno[2][1]=true;
        retorno[2][2]=true;
        retorno[2][3]=true;
        
        return retorno;
    }
}
