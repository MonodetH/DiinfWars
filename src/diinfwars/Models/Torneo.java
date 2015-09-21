/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diinfwars.Models;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author MonodetH
 */
public class Torneo {
    // Atributos
    private Jugador[] jugadores;
    private Jugador[] segundaRonda=new Jugador[4];
    private Jugador[] ultimaRonda = new Jugador[2];
    private Jugador ganador;
    
    
    
    private int oroi;
    private int orok;
    private int mapa;
    
    public Torneo(Jugador[] jugadores,int oroi,int orok,int mapa){
        this.jugadores = shuffleJugadores(jugadores);
        this.oroi=oroi;
        this.orok = orok;
        this.mapa = mapa;
    }
    
    private Jugador[] shuffleJugadores(Jugador[] jugadores){
        List<Jugador> lista = Arrays.asList(jugadores);
        Collections.shuffle(lista);
        return (Jugador[]) lista.toArray();
    }
    
}
