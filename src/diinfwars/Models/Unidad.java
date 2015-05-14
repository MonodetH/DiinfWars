/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diinfwars.Models;

import java.util.ArrayList;

/**
 *
 * @author MonodetH
 */
public abstract class Unidad {
    // Atributos
    private int hp;
    private int hpMax;
    private int estamina;
    private int nivel;
    private int experiencia;
    private int movimiento;
    private float criticalMiss;
    private ArrayList<Ataque> ataques;
    private ArrayList<ModificadorAtributo> modificadores;
    private int mantencion;
    // Static
    public static int costo;
    
}
