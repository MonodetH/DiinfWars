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
    // ATRIBUTOS
    private Casilla[][] matrizCasillas = new Casilla[9][20];
    //private boolean[][] capaRango = new boolean[9][20];
    
    /**
     * Constructor de mapa
     * @param pred mapa seleccionado: 0 = Aleatorio, 1 = Predet1, 2 = Predet2, 3 = Predet3 
     */
    public Mapa(int pred){
        if (pred == 1){
            for(int i = 0;i<9;i++){
                for(int j = 0;j<20;j++){
                    matrizCasillas[i][j] = new Pastos();
                }
            }
        }
        if(pred == 2){
            matrizCasillas[0][0] = new Sherwood(false);
            matrizCasillas[0][19] = new Sherwood(false);
            matrizCasillas[1][0] = new Sherwood(false);
            matrizCasillas[1][8] = new Sherwood(false);
            matrizCasillas[1][12] = new Foro(true);
            matrizCasillas[1][17] = new Sherwood(false);
            matrizCasillas[1][19] = new Sherwood(false);
            matrizCasillas[3][2] = new Kiosco(true); 
            matrizCasillas[3][3] = new Kiosco(true);
            matrizCasillas[3][7] = new Pastos(true);
            matrizCasillas[3][9] = new Laboratorio(true);
            matrizCasillas[3][10] = new Laboratorio(true);
            matrizCasillas[3][12] = new Casitas(true);
            matrizCasillas[3][16] = new Kiosco(true);
            matrizCasillas[3][17] = new Kiosco(true);
            matrizCasillas[4][2] = new Kiosco(true);
            matrizCasillas[4][3] = new Kiosco(true);
            matrizCasillas[4][9] = new Laboratorio(true);
            matrizCasillas[4][10] = new Laboratorio(true);
            matrizCasillas[4][16] = new Kiosco(true);
            matrizCasillas[4][17] = new Kiosco(true);
            matrizCasillas[5][9] = new Laboratorio(true);
            matrizCasillas[5][10] = new Laboratorio(true);
            matrizCasillas[5][12] = new CiteCamp(true);
            matrizCasillas[6][16] = new EAO(true);
            matrizCasillas[6][17] = new EAO(true);
            matrizCasillas[7][0] = new Sherwood(false);
            matrizCasillas[7][2] = new Diinf(true); 
            matrizCasillas[7][5] = new Sherwood(false);
            matrizCasillas[7][14] = new Sherwood(false);
            matrizCasillas[7][16] = new EAO(true);
            matrizCasillas[7][17] = new EAO(true);
            matrizCasillas[7][19] = new Sherwood(false);
            matrizCasillas[8][0] = new Sherwood(false);
            matrizCasillas[8][19] = new Sherwood(false);         
        }
     
        else if(pred == 3){
            matrizCasillas[0][0] = new Sherwood(false);
            matrizCasillas[0][8] = new Laboratorio(true);
            matrizCasillas[0][9] = new Laboratorio(true);
            matrizCasillas[0][10] = new Laboratorio(true);
            matrizCasillas[0][19] = new Sherwood(false);
            matrizCasillas[1][0] = new Sherwood(false);
            matrizCasillas[1][5] = new Sherwood(false);
            matrizCasillas[1][8] = new Laboratorio(true);
            matrizCasillas[1][9] = new Laboratorio(true);
            matrizCasillas[1][10] = new Laboratorio(true);
            matrizCasillas[1][13] = new Sherwood(false);
            matrizCasillas[1][19] = new Sherwood(false);
            matrizCasillas[2][16] = new Casitas(true);
            matrizCasillas[3][2] = new Kiosco(true);
            matrizCasillas[3][3] = new Kiosco(true);
            matrizCasillas[3][9] = new Pastos(true);
            matrizCasillas[3][16] = new Kiosco(true);
            matrizCasillas[3][17] = new Kiosco(true);
            matrizCasillas[4][2] = new Kiosco(true);
            matrizCasillas[4][3] = new Kiosco(true);
            matrizCasillas[4][16] = new Kiosco(true);
            matrizCasillas[4][17] = new Kiosco(true);
            matrizCasillas[6][5] = new Sherwood(false);
            matrizCasillas[6][9] = new Foro(true);
            matrizCasillas[6][13] = new Sherwood(false);
            matrizCasillas[6][16] = new Diinf(true);
            matrizCasillas[7][0] = new Sherwood(false);
            matrizCasillas[7][6] = new CiteCamp(true);
            matrizCasillas[7][11] = new EAO(true);
            matrizCasillas[7][12] = new EAO(true);
            matrizCasillas[7][19] = new Sherwood(false);
            matrizCasillas[8][0] = new Sherwood(false);
            matrizCasillas[8][11] = new EAO(true);
            matrizCasillas[8][12] = new EAO(true);
            matrizCasillas[8][19] = new Sherwood(false);
        }
        
        else if(pred == 4){
            matrizCasillas[0][0] = new Sherwood(false);
            matrizCasillas[0][2] = new Kiosco(true);
            matrizCasillas[0][3] = new Kiosco(true);
            matrizCasillas[0][9] = new Pastos(true);
            matrizCasillas[0][15] = new Kiosco(true);
            matrizCasillas[0][16] = new Kiosco(true);
            matrizCasillas[0][19] = new Sherwood(false);
            matrizCasillas[1][0] = new Sherwood(false);
            matrizCasillas[1][2] = new Kiosco(true);
            matrizCasillas[1][3] = new Kiosco(true);
            matrizCasillas[1][15] = new Kiosco(true);
            matrizCasillas[1][16] = new Kiosco(true);
            matrizCasillas[1][19] = new Sherwood(false);
            matrizCasillas[2][8] = new Sherwood(false);
            matrizCasillas[3][4] = new Casitas(true);
            matrizCasillas[3][13] = new Diinf(true);
            matrizCasillas[5][6] = new Sherwood(false);
            matrizCasillas[5][10] = new Sherwood(false);
            matrizCasillas[5][14] = new Sherwood(false);
            matrizCasillas[7][0] = new Sherwood(false);
            matrizCasillas[7][6] = new EAO(true);
            matrizCasillas[7][7] = new EAO(true);
            matrizCasillas[7][9] = new Laboratorio(true);
            matrizCasillas[7][10] = new Laboratorio(true);
            matrizCasillas[7][11] = new Laboratorio(true);
            matrizCasillas[7][13] = new Foro(true);
            matrizCasillas[7][16] = new CiteCamp(true);
            matrizCasillas[7][19] = new Sherwood(false);
            matrizCasillas[8][0] = new Sherwood(false);
            matrizCasillas[8][6] = new EAO(true);
            matrizCasillas[8][7] = new EAO(true);
            matrizCasillas[8][9] = new Laboratorio(true);
            matrizCasillas[8][10] = new Laboratorio(true);
            matrizCasillas[8][11] = new Laboratorio(true);
            matrizCasillas[8][19] = new Sherwood(false);
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
     * @param rango El rango pedido (1=corto;2=medio;3=largo)
     * @return Una matriz de 9x20 con el rango valido para cierto modo
     */
    public boolean[][] getRango(int modo,int fila,int col,int jugador,int rango){
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
        boolean[][] retorno = new boolean[9][20];
        int columna = (jugador == 1)?0:19;
        if (matrizCasillas[2][columna].getUnidad() == null){retorno[2][columna]=true;}
        if (matrizCasillas[3][columna].getUnidad() == null){retorno[3][columna]=true;}
        if (matrizCasillas[5][columna].getUnidad() == null){retorno[5][columna]=true;}
        if (matrizCasillas[6][columna].getUnidad() == null){retorno[6][columna]=true;}
        return retorno;
    }
    
    private boolean[][] rangoAtaque(int fila,int col, int rango){
        boolean[][] retorno = new boolean[9][20];
        if(matrizCasillas[fila][col].getUnidad().tieneRango(rango)){
            // Rango Corto
            if(rango == 1){
                int filaCambio = fila-1; 
                int filaFinal = fila+1;
                while (filaCambio<=filaFinal){
                    int colCambio = col-1;
                    int colFinal = col+1;
                    while (colCambio<=colFinal){
                        if (filaCambio<0 || filaCambio>8 || colCambio<0 || colCambio>19){
                            colCambio+=1;
                        }
                        else{
                            retorno[filaCambio][colCambio]=true;
                            colCambio+=1;
                        }
                    }
                    filaCambio+=1;           
                }
            }
            // Rango Medio
            else if (rango == 2){
                int filaCambio = fila-2;
                int filaFinal = fila+2;
                while (filaCambio<=filaFinal){
                    int colCambio = col-2;
                    int colFinal = col+2;
                    while (colCambio<=colFinal){
                        if (filaCambio<0 || filaCambio>8 || colCambio<0 || colCambio>19){
                            colCambio+=1;
                        }
                        else{
                            retorno[filaCambio][colCambio]=true;
                            colCambio+=1;
                        }
                    }
                    filaCambio+=1;
                }
            }  
            // Rango Largo
            else if (rango == 3){
                int filaCambio = fila-3;
                int filaFinal = fila+3;
                while (filaCambio<=filaFinal){
                    int colCambio = col-3;
                    int colFinal = col+3;
                    while (colCambio<=colFinal){
                        if (filaCambio<0 || filaCambio>8 || colCambio<0 || colCambio>19){
                            colCambio+=1;
                        }
                        else{
                            retorno[filaCambio][colCambio]=true;
                            colCambio+=1;
                        }
                    }
                    filaCambio+=1;
                }
            }
        }
        return retorno;
    }
        
    public Unidad getUnidad(int fila, int col) {
        return matrizCasillas[fila][col].getUnidad();
    }
}
