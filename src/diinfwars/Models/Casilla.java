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
public abstract class Casilla {
    // Atributos
    protected int defensa;
    protected Unidad unidad = null;
    protected Estratega dueno = null;
    protected boolean horizontal = true;
    protected int parte = 1;
    protected boolean habilitada = true;
    protected String rutaSprite;
    
    
    // Static
    
    // Constructores
    public Casilla(){
        setDefaults();
    }
    public Casilla(boolean isHabilitada){
        setDefaults();
        this.habilitada=isHabilitada;
    }
    public Casilla(boolean isHorizontal,int parte){
        setDefaults();
        this.horizontal = isHorizontal;
        this.parte = parte;
    }
    public Casilla(boolean isHorizontal,int parte,boolean isHabilitada){
        setDefaults();
        this.horizontal = isHorizontal;
        this.parte = parte;
        this.habilitada = isHabilitada;
    }
    
    // Metodos abstractos que deben ser implementados por los hijos
    protected abstract void setDefaults();
    //public abstract void usarHabilidad();
    
    //Metodos genericos que serán heredados
    public boolean setUnidad(Unidad unidad){
        if (this.unidad == null){
            this.unidad = unidad;
            return true;
        }else{
            return false;
        }
    }
    
    public Unidad getUnidad(){
        return this.unidad;
    }
    
    public Unidad popUnidad(){
        Unidad respuesta = this.unidad;
        this.unidad = null;
        return respuesta;
    }
    
    public String getSprite(){
        return this.rutaSprite;
    }
    
    
    
}
