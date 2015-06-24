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
public class Estratega {
    // Atributos
    private int equipo;
    private int oro;
    private ArrayList<Unidad> unidades = new ArrayList<Unidad>();
    private int asTactico = 0;
    private int asCooldown;
    
    /**
     * Costructor de la clase Estratega
     * @param equipo Equipo al cual lidera: 1: equipo azul, 2: equipo rojo
     * @param oroInicial
     * @param as As tactico que se ha helegido. valor entero
     * @param puntosCorto Puntos de ataque asignados al rango corto de profesor
     * @param puntosMedio Puntos asignados al rango medio de profesor
     * @param naturalezaProfesor1 Nombre de una naturaleza del profesor
     * @param naturalezaProfesor2 Nombre de una naturaleza del profesor
     */
    public Estratega(int equipo,int oroInicial,int as,int puntosCorto,int puntosMedio,String naturalezaProfesor1, String naturalezaProfesor2){
        this.equipo = equipo;
        oro = oroInicial;
        asTactico = as;
        asCooldown = 11;
        
        unidades.add(new Profesor(equipo,puntosCorto,puntosMedio,naturalezaProfesor1,naturalezaProfesor2));
    }
    
    /**
     * Funcion que recluta una nueva unidad y la agrega a la lista de unidades
     * @param tipoUnidad String con el tipo de unidad
     * @return La unidad reclutada. null si hubo un error
     */
    public Unidad reclutar(String tipoUnidad){
        Unidad nuevaUnidad;
        
        switch (tipoUnidad){
            case "Alumno":
                nuevaUnidad = new Alumno(equipo);
                break;
            case "AlumnoNivelSuperior":
                nuevaUnidad = new AlumnoNivelSuperior(equipo);
                break;
            case "Ayudante":
                nuevaUnidad = new Ayudante(equipo);
                break;
            case "Cachorro":
                nuevaUnidad = new Cachorro(equipo);
                break;
            case "CoordinadorAyudantes":
                nuevaUnidad = new CoordinadorAyudantes(equipo);
                break;
            case "Pame":
                nuevaUnidad = new Pame(equipo);
                break;
            default:
                nuevaUnidad = null;
                break;
        }
        
        if (nuevaUnidad != null){unidades.add(nuevaUnidad);}
        
        return nuevaUnidad;
    }
    
    /**
     * Obtiene la unidad profesor
     * @return La unidad. null si no existe
     */
    public Unidad getProfesor(){
        Unidad candidato = unidades.get(0);
        return (candidato instanceof Profesor)?candidato:null; // si candidato es profesor lo retorna, sino null
    }
    
    public int getOro(){
        return this.oro;
    }
}
