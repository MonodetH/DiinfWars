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
    private int oro;
    private ArrayList<Unidad> unidades = new ArrayList<Unidad>();
    private int asTactico = 0;
    private int asCooldown;
    
    public Estratega(int oroInicial,int as){
        oro = oroInicial;
        asTactico = as;
        unidades.add(new Profesor());
        asCooldown = 11;
    }
    
    public Unidad reclutar(String tipoUnidad){
        Unidad nuevaUnidad;
        
        switch (tipoUnidad){
            case "Alumno":
                nuevaUnidad = new Alumno();
                break;
            default:
                nuevaUnidad = null;
                break;
        }
        
        return nuevaUnidad;
    }
    
    public Unidad getProfesor(){
        Unidad candidato = unidades.get(0);
        return (candidato instanceof Profesor)?candidato:null; // si candidato es profesor lo retorna, sino null
    }
}
