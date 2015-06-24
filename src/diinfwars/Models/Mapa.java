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
            matrizCasillas[2][0] = new Laboratorio(false,1,true);
            matrizCasillas[3][0] = new Laboratorio(false,2,true);
            matrizCasillas[4][0] = new Laboratorio(false,3,true);
            matrizCasillas[5][0] = new Laboratorio(false,4,true);
            matrizCasillas[6][0] = new Laboratorio(false,5,true);
            matrizCasillas[2][19] = new Laboratorio(false,1,true);
            matrizCasillas[3][19] = new Laboratorio(false,2,true);
            matrizCasillas[4][19] = new Laboratorio(false,3,true);
            matrizCasillas[5][19] = new Laboratorio(false,4,true);
            matrizCasillas[6][19] = new Laboratorio(false,5,true);
            matrizCasillas[0][0] = new Sherwood(false);
            matrizCasillas[0][19] = new Sherwood(false);
            matrizCasillas[1][0] = new Sherwood(false);
            matrizCasillas[1][8] = new Sherwood(false);
            matrizCasillas[1][12] = new Foro(true,1,true);
            matrizCasillas[1][17] = new Sherwood(false);
            matrizCasillas[1][19] = new Sherwood(false);
            matrizCasillas[3][2] = new Kiosco(true,1,true); 
            matrizCasillas[3][3] = new Kiosco(true,2,true);
            matrizCasillas[3][7] = new Pastos(true,1,true);
            matrizCasillas[3][9] = new Laboratorio(false,1,true);
            matrizCasillas[3][10] = new Laboratorio(false,2,true);
            matrizCasillas[3][12] = new Casitas(true,1,true);
            matrizCasillas[3][16] = new Kiosco(true,1,true);
            matrizCasillas[3][17] = new Kiosco(true,2,true);
            matrizCasillas[4][2] = new Kiosco(true,3,true);
            matrizCasillas[4][3] = new Kiosco(true,4,true);
            matrizCasillas[4][9] = new Laboratorio(true,3,true);
            matrizCasillas[4][10] = new Laboratorio(true,4,true);
            matrizCasillas[4][16] = new Kiosco(true,3,true);
            matrizCasillas[4][17] = new Kiosco(true,4,true);
            matrizCasillas[5][9] = new Laboratorio(true,5,true);
            matrizCasillas[5][10] = new Laboratorio(true,6,true);
            matrizCasillas[5][12] = new CiteCamp(true,1,true);
            matrizCasillas[6][16] = new EAO(true,1,true);
            matrizCasillas[6][17] = new EAO(true,2,true);
            matrizCasillas[7][0] = new Sherwood(false);
            matrizCasillas[7][2] = new Diinf(true,1,true); 
            matrizCasillas[7][5] = new Sherwood(false);
            matrizCasillas[7][14] = new Sherwood(false);
            matrizCasillas[7][16] = new EAO(true,3,true);
            matrizCasillas[7][17] = new EAO(true,4,true);
            matrizCasillas[7][19] = new Sherwood(false);
            matrizCasillas[8][0] = new Sherwood(false);
            matrizCasillas[8][19] = new Sherwood(false);         
        }
     
        else if(pred == 3){
            matrizCasillas[2][0] = new Laboratorio(false,1,true);
            matrizCasillas[3][0] = new Laboratorio(false,2,true);
            matrizCasillas[4][0] = new Laboratorio(false,3,true);
            matrizCasillas[5][0] = new Laboratorio(false,4,true);
            matrizCasillas[6][0] = new Laboratorio(false,5,true);
            matrizCasillas[2][19] = new Laboratorio(false,1,true);
            matrizCasillas[3][19] = new Laboratorio(false,2,true);
            matrizCasillas[4][19] = new Laboratorio(false,3,true);
            matrizCasillas[5][19] = new Laboratorio(false,4,true);
            matrizCasillas[6][19] = new Laboratorio(false,5,true);
            matrizCasillas[0][0] = new Sherwood(false);
            matrizCasillas[0][8] = new Laboratorio(true,1,true);
            matrizCasillas[0][9] = new Laboratorio(true,2,true);
            matrizCasillas[0][10] = new Laboratorio(true,3,true);
            matrizCasillas[0][19] = new Sherwood(false);
            matrizCasillas[1][0] = new Sherwood(false);
            matrizCasillas[1][5] = new Sherwood(false);
            matrizCasillas[1][8] = new Laboratorio(true,4,true);
            matrizCasillas[1][9] = new Laboratorio(true,5,true);
            matrizCasillas[1][10] = new Laboratorio(true,6,true);
            matrizCasillas[1][13] = new Sherwood(false);
            matrizCasillas[1][19] = new Sherwood(false);
            matrizCasillas[2][16] = new Casitas(true,1,true);
            matrizCasillas[3][2] = new Kiosco(true,1,true);
            matrizCasillas[3][3] = new Kiosco(true,2,true);
            matrizCasillas[3][9] = new Pastos(true,1,true);
            matrizCasillas[3][16] = new Kiosco(true,1,true);
            matrizCasillas[3][17] = new Kiosco(true,2,true);
            matrizCasillas[4][2] = new Kiosco(true,3,true);
            matrizCasillas[4][3] = new Kiosco(true,4,true);
            matrizCasillas[4][16] = new Kiosco(true,3,true);
            matrizCasillas[4][17] = new Kiosco(true,4,true);
            matrizCasillas[6][5] = new Sherwood(false);
            matrizCasillas[6][9] = new Foro(true,1,true);
            matrizCasillas[6][13] = new Sherwood(false);
            matrizCasillas[6][16] = new Diinf(true,1,true);
            matrizCasillas[7][0] = new Sherwood(false);
            matrizCasillas[7][6] = new CiteCamp(true,1,true);
            matrizCasillas[7][11] = new EAO(true,1,true);
            matrizCasillas[7][12] = new EAO(true,2,true);
            matrizCasillas[7][19] = new Sherwood(false);
            matrizCasillas[8][0] = new Sherwood(false);
            matrizCasillas[8][11] = new EAO(true,3,true);
            matrizCasillas[8][12] = new EAO(true,4,true);
            matrizCasillas[8][19] = new Sherwood(false);
        }
        
        else if(pred == 4){
            matrizCasillas[2][0] = new Laboratorio(false,1,true);
            matrizCasillas[3][0] = new Laboratorio(false,2,true);
            matrizCasillas[4][0] = new Laboratorio(false,3,true);
            matrizCasillas[5][0] = new Laboratorio(false,4,true);
            matrizCasillas[6][0] = new Laboratorio(false,5,true);
            matrizCasillas[2][19] = new Laboratorio(false,1,true);
            matrizCasillas[3][19] = new Laboratorio(false,2,true);
            matrizCasillas[4][19] = new Laboratorio(false,3,true);
            matrizCasillas[5][19] = new Laboratorio(false,4,true);
            matrizCasillas[6][19] = new Laboratorio(false,5,true);
            matrizCasillas[0][0] = new Sherwood(false);
            matrizCasillas[0][2] = new Kiosco(true,1,true);
            matrizCasillas[0][3] = new Kiosco(true,2,true);
            matrizCasillas[0][9] = new Pastos(true,1,true);
            matrizCasillas[0][15] = new Kiosco(true,1,true);
            matrizCasillas[0][16] = new Kiosco(true,2,true);
            matrizCasillas[0][19] = new Sherwood(false);
            matrizCasillas[1][0] = new Sherwood(false);
            matrizCasillas[1][2] = new Kiosco(true,3,true);
            matrizCasillas[1][3] = new Kiosco(true,4,true);
            matrizCasillas[1][15] = new Kiosco(true,3,true);
            matrizCasillas[1][16] = new Kiosco(true,4,true);
            matrizCasillas[1][19] = new Sherwood(false);
            matrizCasillas[2][8] = new Sherwood(false);
            matrizCasillas[3][4] = new Casitas(true,1,true);
            matrizCasillas[3][13] = new Diinf(true,1,true);
            matrizCasillas[5][6] = new Sherwood(false);
            matrizCasillas[5][10] = new Sherwood(false);
            matrizCasillas[5][14] = new Sherwood(false);
            matrizCasillas[7][0] = new Sherwood(false);
            matrizCasillas[7][6] = new EAO(true,1,true);
            matrizCasillas[7][7] = new EAO(true,2,true);
            matrizCasillas[7][9] = new Laboratorio(true,1,true);
            matrizCasillas[7][10] = new Laboratorio(true,2,true);
            matrizCasillas[7][11] = new Laboratorio(true,3,true);
            matrizCasillas[7][13] = new Foro(true,1,true);
            matrizCasillas[7][16] = new CiteCamp(true,1,true);
            matrizCasillas[7][19] = new Sherwood(false);
            matrizCasillas[8][0] = new Sherwood(false);
            matrizCasillas[8][6] = new EAO(true,3,true);
            matrizCasillas[8][7] = new EAO(true,4,true);
            matrizCasillas[8][9] = new Laboratorio(true,4,true);
            matrizCasillas[8][10] = new Laboratorio(true,5,true);
            matrizCasillas[8][11] = new Laboratorio(true,6,true);
            matrizCasillas[8][19] = new Sherwood(false);
        }
        
        //Se rellena las casillas vacias con Calles
        for(int i = 0;i<9;i++){
            for(int j = 0;j<20;j++){
                if (matrizCasillas[i][j] == null){
                    matrizCasillas[i][j] = new Calle();
                }
            }
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
        if (modo == 1){return rangoMover(fila,col);}
        else if (modo == 2){return rangoAtaque(fila,col,rango);}
        else if (modo == 3){
            boolean[][] retorno = new boolean[9][20];
            retorno[1][1]=true;
            retorno[1][3]=true;
            retorno[2][1]=true;
            retorno[2][2]=true;
            retorno[2][3]=true;

            return retorno;
            }
        else if (modo == 4){return rangoReclutar(jugador);}
        
        return new boolean[9][20];
    }

    private boolean[][] rangoMover(int fila, int col) {
        boolean[][] retorno = new boolean[9][20];
        if (matrizCasillas[fila][col].getUnidad() != null && matrizCasillas[fila][col].isHabilitada()){
            int[][] preRango = new int[9][20];
            preRangoMover(preRango,fila,col,matrizCasillas[fila][col].getUnidad().getMovimientos());
            
            for(int i=0;i<9;i++){
                for(int j=0;j<20;j++){
                    if(preRango[i][j]>0){
                        retorno[i][j] = true;
                    }
                }
            }
        }
        retorno[fila][col] = false; // no se puede mover donde el esta parado
        return retorno;
    }
    private void preRangoMover(int[][] matrizRango, int fila,int col,int movimientos){
        if(movimientos > 0 && movimientos+1 > matrizRango[fila][col] && matrizCasillas[fila][col].isHabilitada()){
            matrizRango[fila][col] = movimientos+1;
            
            if(fila-1 >= 0){preRangoMover(matrizRango,fila-1,col,movimientos-1);} // Arriba
            if(col+1 < 20){preRangoMover(matrizRango,fila,col+1,movimientos-1);} // Derecha
            if(fila+1 < 9){preRangoMover(matrizRango,fila+1,col,movimientos-1);} // Abajo
            if(col-1 >= 0){preRangoMover(matrizRango,fila,col-1,movimientos-1);} // Izquierda
        }
    }
    
    /**
     * Esta funcion retorna el rango para el modo reclutar. 
     * @param jugador El jugador que gatilla la peticion (los valores son 1 o 2)
     * @return Matriz de 9x20 con rango valido
     */
    private boolean[][] rangoReclutar(int jugador) {        
        boolean[][] retorno = new boolean[9][20];
        int columna = (jugador == 1)?0:19;

        // Si no existe profesor en el trono
        if (!(matrizCasillas[4][columna].getUnidad() instanceof Profesor)){
            return retorno;
        }

        if (matrizCasillas[2][columna].getUnidad() == null){retorno[2][columna]=true;}
        if (matrizCasillas[3][columna].getUnidad() == null){retorno[3][columna]=true;}
        if (matrizCasillas[5][columna].getUnidad() == null){retorno[5][columna]=true;}
        if (matrizCasillas[6][columna].getUnidad() == null){retorno[6][columna]=true;}
        return retorno;
    }
    
    private boolean[][] rangoAtaque(int fila,int col, int rango){
        boolean[][] retorno = new boolean[9][20];
        if(matrizCasillas[fila][col].getUnidad() != null && matrizCasillas[fila][col].getUnidad().tieneRango(rango)){
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
        retorno[fila][col] = false; // no se puede atacar a si mismo
        return retorno;
    }
        
    public Unidad getUnidad(int fila, int col) {
        return matrizCasillas[fila][col].getUnidad();
    }
}
