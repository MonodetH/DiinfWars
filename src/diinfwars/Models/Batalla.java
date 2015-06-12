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
public class Batalla {
    // Atributos
    private Mapa mapa;
    private Jugador jugador1;
    private Jugador jugador2;
    private Estratega estratega1;
    private Estratega estratega2;
    
    /**
     * Constructor de batalla
     * @param j1 Jugagor 1
     * @param j2 Jugador 2
     * @param m Mapa preseteado o aleatorio
     * @param orok Oro dado por cada kiosco
     * @param oroi1 Oro inicial del jugador 1
     * @param oroi2 Oro inicial del jugador 2
     */
    public Batalla(Jugador j1,Jugador j2,Mapa m,int orok,int oroi1, int oroi2){
        mapa = m;
        jugador1 = j1;
        jugador2 = j2;
        estratega1 = new Estratega(1,oroi1,1);
        estratega2 = new Estratega(2,oroi2,1);
    }
    
    public Mapa getMapa(){return mapa;}
    public Jugador getJugador1(){return this.jugador1;}
    public Jugador getJugador2(){return this.jugador2;}
}
