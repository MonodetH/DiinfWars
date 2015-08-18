/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diinfwars.Models;

import java.util.ArrayList;
import java.util.Iterator;

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
     * @param as As tactico que se ha elegido. valor entero
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
        
        if (nuevaUnidad != null && this.oro >= nuevaUnidad.getCosto()){
            this.oro -= nuevaUnidad.getCosto();
            unidades.add(nuevaUnidad);
            return nuevaUnidad;
        }
        
        return null;
    }
    
    
    public int[] cobrarMantencion(){
        int mantencion = getMantencion();
        this.oro -= mantencion;
        if (oro < 0){oro = 0;}
        return new int[]{oro,mantencion};
    }
    
    public void restarTurnoAS(){
        if(asCooldown > 0){asCooldown--;}
    }
    
    public ArrayList<Unidad> restarTurnoMuertos(){
        ArrayList<Unidad> eliminados = new ArrayList<Unidad>(); 
        Iterator<Unidad> iterador = unidades.iterator();
        while(iterador.hasNext()){
            Unidad unidad = iterador.next();
            if(unidad.isDead()){
                unidad.restarTurnoMuerto();
            }
            if(unidad.isReallyDead()){
                eliminados.add(unidad);
            }
        }
        iterador = eliminados.iterator();
        while(iterador.hasNext()){
            unidades.remove(iterador.next());
        }
        
        return eliminados;
    }
    
    public void restarTurnoMod(){
        Iterator<Unidad> iterador = unidades.iterator();
        while(iterador.hasNext()){
            Unidad unidad = iterador.next();
            unidad.restarMod();
        }
    }
    
    /**
     * Obtiene la unidad profesor
     * @return La unidad. null si no existe
     */
    public Unidad getProfesor(){
        Unidad candidato = unidades.get(0);
        return (candidato instanceof Profesor)?candidato:null; // si candidato es profesor lo retorna, sino null
    }
    public int getMantencion(){
        int mantencion = 0;
        Iterator<Unidad> iterador = unidades.iterator();
        while(iterador.hasNext()){
            Unidad unidad = iterador.next();
            if(!unidad.isDead()){
                mantencion += unidad.getMantencion();
            }
        }
        return mantencion;
    }
    public int getOro(){return this.oro;}
}
