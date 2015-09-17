/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diinfwars.Models.Unidades;

import diinfwars.Models.Ataque;
import diinfwars.Models.ModificadorAtributo;
import diinfwars.Models.Unidad;

/**
 *
 * @author MonodetH
 */
public class Profesor extends Unidad {
    public static int sCosto = 0;
    public static String sRutaSprite1 = "/images/SpriteProfesor.gif";
    public static String sRutaSprite2 = "/images/SpriteProfesor.gif";

    public Profesor(int equipo) {
        super(equipo);
    }
    
    
    public Profesor(int equipo, int puntosCorto, int puntosMedio, String naturaleza1, String naturaleza2){
        setDefaults(equipo);
        
        // NATURALEZAS
        if (naturaleza1 == "Estudioso" || naturaleza2 == "Estudioso")
        {
            modificadores.add(new ModificadorAtributo("dano",1,-1));
            System.out.println("Estudioso");
        }
        if (naturaleza1 == "Normal" || naturaleza2 == "Normal")
        {
            // No existe modificacion
            System.out.println("Normal");
        }
        if (naturaleza1 == "Deportista" || naturaleza2 == "Deportista")
        {
            modificadores.add(new ModificadorAtributo("movimiento",1,-1));
            System.out.println("Deportista");
        }
        if (naturaleza1 == "Incoherente" || naturaleza2 == "Incoherente")
        {
            modificadores.add(new ModificadorAtributo("cantidadGolpes",1,-1));
            System.out.println("Incoherente");
        }
        if (naturaleza1 == "Tortuga" || naturaleza2 == "Tortuga")
        {
            modificadores.add(new ModificadorAtributo("movimiento",-1,-1));
            System.out.println("Tortuga");
        }
        if (naturaleza1 == "Deprimido" || naturaleza2 == "Deprimido")
        {
            modificadores.add(new ModificadorAtributo("dano",-1,-1));
            System.out.println("Deprimido");
        }
        if (naturaleza1 == "Carretero" || naturaleza2 == "Carretero")
        {
            modificadores.add(new ModificadorAtributo("cantidadGolpes",-1,-1));
            System.out.println("Carretero");
        }
        
        
        if(naturaleza1 == naturaleza2){
            modificadores.add(new ModificadorAtributo());
            System.out.println("Aweonao");
        }

        
        // ATAQUES 
        ataques.add(new Ataque(puntosCorto,2,1));
        ataques.add(new Ataque(puntosMedio,3,2));
    }
    
    @Override
    protected void setDefaults(int equipo) {
        // ATRIBUTOS
        this.equipo = equipo;
        this.hp = 75;
        this.hpMax = 75;
        this.movimiento = 3;
        this.mantencion = 0;
        this.costo = 0;
        this.expMax = 50;

        this.rutaSprite1 = Profesor.sRutaSprite1;
        this.rutaSprite2 = Profesor.sRutaSprite2;
    }

    @Override
    protected void subirNivel() {
        this.nivel++;
        this.expMax += 15;
        this.hpMax += hpMax/10;
        modificadores.add(new ModificadorAtributo("dano",1,-1));
        if(nivel%2 == 1){
            modificadores.add(new ModificadorAtributo("cantidadGolpes",1,-1));
        }
    }
    
}