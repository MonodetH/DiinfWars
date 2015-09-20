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
        // Math.random() del 0.0 al 1.0 excluido el 1.0
        // Math.random()*7 del 0.0 al 7.0 excluido el 7.0
        // Math.random()*7+1 del 1.0 al 8.0 excluido el 8.0 
        // Math.floor devuelve el entero del valor random.
        int aleatorio = (int) Math.floor(Math.random()*7+1);
 
        if (aleatorio == 1)
        { // Carretero, modifica cantidad de golpes
            this.atributo = "cantidadGolpes";
            this.modificacion = -1;
            this.turnosRestantes = -1;
        }
        else if (aleatorio == 2)
        { // Estudioso, modifica el daño
            this.atributo = "dano";
            this.modificacion = 99;
            this.turnosRestantes = -1;
        }
        else if (aleatorio == 3)
        { // Incoherente, modifica cantidad de golpes
            this.atributo = "cantidadGolpes";
            this.modificacion = 1;
            this.turnosRestantes = -1;
        }
        else if (aleatorio == 4)
        { // Normal, no modifica nada
            this.atributo = "";
            this.modificacion = 0;
            this.turnosRestantes = -1;
        }
        else if (aleatorio == 5)
        { // Deportista, modifica cantidad de movimientos
            this.atributo = "movimiento";
            this.modificacion = 1;
            this.turnosRestantes = -1;
        }
        else if (aleatorio == 6)
        { // Deprimido, modifica el daño
            this.atributo = "dano";
            this.modificacion = -1;
            this.turnosRestantes = -1;
        }
        else if (aleatorio == 7)
        { // Tortuga, modifica cantidad de movimientos
            this.atributo = "movimiento";
            this.modificacion = -1;
            this.turnosRestantes = -1;
        }
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
