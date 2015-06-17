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
    private int oroKiosco;
    
    /**
     * Constructor de clase Batalla
     * @param m Mapa del enfrentamiento
     * @param oroKiosco Oro que da cada kiosco
     * @param j1 Jugador 1
     * @param oroInicialJ1 Oro inicial del jugador1
     * @param asJ1 As Tactico elegido por jugador1
     * @param ptosCortoJ1 Puntos asignados al ataque corto del profesor de jugador1
     * @param ptosMedioJ1 Puntos asignados al ataque medio del profesor de jugador1
     * @param natur1J1 Una de las dos naturalezas del profesor de jugador1
     * @param natur2J1 Una de las dos naturalezas del profesor de jugador1
     * @param j2 Jugador 2
     * @param oroInicialJ2 Oro inicial del jugador2
     * @param asJ2 As Tactico del jugador2
     * @param ptosCortoJ2 Puntos rango corto del profesor de jugador2
     * @param ptosMedioJ2 Punto de rango medio del profesor de jugador2
     * @param natur1J2 Una de las dos naturalezas del profesor de jugador2
     * @param natur2J2 Una de las dos naturalezas del profesor de jugador2
     */
    public Batalla(Mapa m,int oroKiosco,
            Jugador j1,int oroInicialJ1,int asJ1,int ptosCortoJ1,int ptosMedioJ1,String natur1J1,String natur2J1,
            Jugador j2,int oroInicialJ2,int asJ2,int ptosCortoJ2,int ptosMedioJ2,String natur1J2,String natur2J2){
        mapa = m;
        jugador1 = j1;
        jugador2 = j2;
        estratega1 = new Estratega(1,oroInicialJ1,asJ1,ptosCortoJ1,ptosMedioJ1,natur1J1,natur2J1);
        estratega2 = new Estratega(2,oroInicialJ2,asJ2,ptosCortoJ2,ptosMedioJ2,natur1J2,natur2J2);
        
        // se ubican los profesores
        mapa.ubicarUnidad(estratega1.getProfesor(),4,0);
        mapa.ubicarUnidad(estratega2.getProfesor(),4,19);
        
        this.oroKiosco = oroKiosco;
    }
    
    public Mapa getMapa(){return mapa;}
    public Jugador getJugador1(){return this.jugador1;}
    public Jugador getJugador2(){return this.jugador2;}
    public int getOroKiosco(){return this.oroKiosco;}
}
