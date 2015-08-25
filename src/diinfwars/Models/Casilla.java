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
    // ATRIBUTOS
    protected int defensa;
    protected Unidad unidad = null;
    protected Edificio edificio = null;
    //protected Estratega dueno = null;
    //protected boolean horizontal = true;
    //protected int parte = 1;
    protected boolean habilitada = true;
    protected String rutaSprite;
    protected String habEntrada = "";
    protected String habPersistente = "";
    protected String tipo = "";
    
    
    // Static
    
    // CONSTRUCTORES
    public Casilla(){
        setDefaults();
    }
    public Casilla(boolean isHabilitada){
        setDefaults();
        this.habilitada=isHabilitada;
    }
    public Casilla(Edificio edificio){
        setDefaults();
        this.edificio = edificio;
    }
    public Casilla(Edificio edificio, boolean isHabilitada){
        setDefaults();
        this.edificio = edificio;
        this.habilitada=isHabilitada;
    }
    
    // Metodos abstractos que deben ser implementados por los hijos
    protected abstract void setDefaults();
    
    
    //Metodos genericos que serán heredados
    public boolean setUnidad(Unidad unidad){
        if (this.unidad == null){
            this.unidad = unidad;
            if (this.unidad != null){
                if(edificio != null){this.edificio.agregarUnidad(unidad.getEquipo());}
                return true;
            }
        }
            return false;
    }
    
    public void setEdificio(Edificio edificio){this.edificio = edificio;}
    
    public Unidad getUnidad(){
        return this.unidad;
    }
    
    public Unidad popUnidad(){
        Unidad respuesta = this.unidad;
        this.unidad = null;
        if (respuesta != null && edificio != null){
            this.edificio.quitarUnidad(respuesta.getEquipo());
        }
        return respuesta;
    }
    
    public String getTipo(){return this.tipo;}
    public int getDueno(){
        if(edificio != null){return edificio.getDueno();}
        return -1;
    }
    public String getSprite(){return this.rutaSprite;}
    public String getHabilidadEntrada(){return this.habEntrada;}
    public String getHabilidadPersistente(){return this.habPersistente;}
    public int getDefensa(){return this.defensa;}
    public boolean isHabilitada(){return this.habilitada;}
    
}
