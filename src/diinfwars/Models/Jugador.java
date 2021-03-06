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
public class Jugador {
    // Atributos
    private String nombre;
    private boolean isCpu = false;
    private int victorias = 0;
    private int derrotas = 0;
    private int torneosGanados = 0;
    private int unidadesReclutadas = 0;
    
    /**
     * Constructor de jugador
     * @param nombre String que contiene el nombre del jugador
     */
    public Jugador(String nombre){
        this.nombre=nombre;
    }
    public Jugador(String nombre,boolean cpu){
        this.nombre=nombre;
        this.isCpu = cpu;
    }
    
    
    public String getNombre(){return this.nombre;}
    public int getVictorias(){return this.victorias;}
    public int getDerrotas(){return this.derrotas;}
    public boolean isCpu(){return this.isCpu;}
    
}
