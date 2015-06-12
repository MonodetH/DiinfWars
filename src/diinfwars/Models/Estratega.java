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
    
    public Estratega(int equipo,int oroInicial,int as){
        this.equipo = equipo;
        oro = oroInicial;
        asTactico = as;
        unidades.add(new Profesor(equipo));
        asCooldown = 11;
    }
    
    /**
     * Funcion que recluta una nueva unidad y la agrega a la lista de unidades
     * @param tipoUnidad String con el tipo de unidad
     * @return La unidad reclutada. null si hubo un error
     */
    public Unidad reclutar(String tipoUnidad){
        Unidad nuevaUnidad;
        
        /* 
            Se DEBE implementar un case por cada tipo de unidad mas un default
            que lo deje en null
        */
        switch (tipoUnidad){
            case "Alumno":
                nuevaUnidad = new Alumno(equipo);
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
}
