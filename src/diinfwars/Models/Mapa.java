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
    private Casilla[][] matrizCasillas = new Casilla[9][20];
    //private boolean[][] capaRango = new boolean[9][20];
    
    /**
     * Constructor de mapa
     * @param pred mapa seleccionado: 0 = Aleatorio, 1 = Predet1, 2 = Predet2, 3 = Predet3 
     */
    public Mapa(int pred){
        String[][] mapa1 = new String[9][20];
        mapa1 = 
        
        if (pred == 1){
            for(int i = 0;i<9;i++){
                for(int j = 0;j<20;j++){
                    matrizCasillas[i][j] = new Pastos();
                }
            }
        }else if(pred == 2){
            matrizCasillas[0][0] = new Sherwood()
        }
    }
    
    /**
     * Esta funcion toma una unidad de una casilla inicial y la reubica en una
     * casilla final
     * 
     * @param filaInicial fila de la casilla inicial
     * @param colInicial Columna de la casilla inicial
     * @param filaFinal Fila de la casilla de llegada
     * @param colFinal Fila de la casilla de llegada
     * @return Retorna true o false dependiendo si la unidad puso ser movida o no
     * @see Casilla#popUnidad() 
     * @see Casilla#setUnidad(diinfwars.Models.Unidad) 
     */
    public boolean moverUnidad(int filaInicial, int colInicial,int filaFinal,int colFinal){
        Unidad unidad = matrizCasillas[filaInicial][colInicial].popUnidad();
        if (matrizCasillas[filaFinal][colFinal].setUnidad(unidad)){ //trata de reubicar la unidad
            return true;
        }else{
            matrizCasillas[filaInicial][colInicial].setUnidad(unidad); // la devuelve a su posicion inicial
        }
        return false;
    }
    
    public boolean ubicarUnidad(Unidad unidad,int fila,int col){
        matrizCasillas[fila][col].setUnidad(unidad); //trata de ubicar la unidad
        if (matrizCasillas[fila][col].getUnidad()!= null){return true;}
        return false;
    }
    
    /**Retorna la matriz de casillas*/
    public Casilla[][] getCasillas(){return this.matrizCasillas;}
    
    /**
     * Esta funcion genera una matriz de 9x20 con la direccion a los sprites
     * del terreno de cada casilla
     * @return Matriz de string de 9x20 con la direccion del sprite del terreno de cada casilla
     */
    public String[][] terrenoToString(){
        String[][] retorno = new String[9][20];
        for(int i=0;i<9;i++){
            for(int j=0;j<20;j++){
                retorno[i][j] = matrizCasillas[i][j].getSprite();
            }
        }
        return retorno;
    }
    
    /**
     * Esta funcion genera una matriz de 9x20 con la direccion a los sprites
     * de la unidad de cada casilla
     * @return Matriz de string de 9x20 con la direccion del sprite de la unidad de cada casilla
     */
    public String[][] unidadesToString(){
        String[][] retorno = new String[9][20];
        for(int i=0;i<9;i++){
            for(int j=0;j<20;j++){
                if(matrizCasillas[i][j].getUnidad() != null){
                    retorno[i][j] = matrizCasillas[i][j].getUnidad().getSprite();
                }else{
                    retorno[i][j] = null;
                }
            }
        }
        return retorno;
    }
    
    /**
     * Esta funcion crea una matriz de booleanos de 9x20 y setea true, solo el rango valido
     * para cierto tipo de modo, tomando el cuenta todos los tipos de restricciones.
     * 
     * @param modo  0: sin modo (retorna rango vacio); 1: mover; 2: Ataque; 3 = As tactico; 5 = Reclutar.
     * @param fila La fila de la posicion inicial desde donde se calcula el rango
     * @param col La columna de la posicion inicial desde donde se calcula el rango
     * @param jugador El jugador que gatilla la peticion
     * @return Una matriz de 9x20 con el rango valido para cierto modo
     */
    public boolean[][] getRango(int modo,int fila,int col,int jugador){
        /*
            Para esto recomiendo usar un metodo privado por modo, para no mezclar todo el codigo.
            Se muestra como funciona esto para el modo 4, se debe terminar de implementar este
            y ademas implementar el de los demas modos.
        */
        if (modo == 4){return rangoReclutar(jugador);}
        boolean[][] retorno = new boolean[9][20];
        retorno[1][1]=true;
        retorno[1][3]=true;
        retorno[2][1]=true;
        retorno[2][2]=true;
        retorno[2][3]=true;
        
        return retorno;
    }

    /**
     * Esta funcion retorna el rango para el modo reclutar. 
     * @param jugador El jugador que gatilla la peticion (los valores son 1 o 2)
     * @return Matriz de 9x20 con rango valido
     */
    private boolean[][] rangoReclutar(int jugador) {
        /*
            Comentario de ayuda (borrar cuando ya no se necesite):
            -Dependiendo de cual jugador pida el rango, las posiciones pueden ser
            en el laboratorio de profesor 1 o 2 (en cada extremo).
            -Se debe comprobar que en cada posicion no exista ya una unidad, para
            asi no tener problemas ubicando una unidad sobre otra
        
            -A esta funcion solo le falta la comprobacion!
        */
        
        boolean[][] retorno = new boolean[9][20];
        int columna = (jugador == 1)?0:19;
        retorno[2][columna]=true;
        retorno[3][columna]=true;
        retorno[5][columna]=true;
        retorno[6][columna]=true;
        
        return retorno;
    }

    public Unidad getUnidad(int fila, int col) {
        return matrizCasillas[fila][col].getUnidad();
    }
}
