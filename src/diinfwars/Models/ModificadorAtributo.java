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
public class ModificadorAtributo {
    private String atributo;
    private int modificacion;
    private int turnosRestantes;
    
    /**
     * Constructor que genera una naturaleza al azar
     */
    public ModificadorAtributo(){
        /*
            IMPLEMENTAR
        */
    }
    
    /**
     * Constructor para un modificador ya conocido
     * @param atributo El nombre o codigo del atributo
     * @param valor El valor de la modificacion
     * @param turnos Cantidad de turnos que dura el modificador. -1 para duracion permanente
     */
    public ModificadorAtributo(String atributo, int valor, int turnos){
        this.atributo = atributo;
        this.modificacion = valor;
        this.turnosRestantes = turnos;
    }
    
    /**
     * Funcion que resta un turno a la cantidad de turnos de la modificacion
     * @return true si sigue siendo un modificador valido, false si debe ser eliminado
     */
    public boolean reducirTurno(){
        if (turnosRestantes > 0){turnosRestantes--;}
        return (turnosRestantes != 0);
    }
    
    public String getAtributo(){return atributo;}
    public int getValor(){return modificacion;}
    
}
