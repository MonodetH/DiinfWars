/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diinfwars.Models;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 *
 * @author MonodetH
 */
public abstract class Unidad {
    // ATRIBUTOS   
    protected int equipo;
    protected int hp;
    protected int hpMax;
    protected int estamina = 100;
    protected int nivel = 1;
    protected int experiencia = 0;
    protected int expMax;
    protected int movimiento;
    protected int criticalMiss = 5;
    protected int heal=0;
    protected boolean inmovil = false;
    protected ArrayList<Ataque> ataques = new ArrayList<>();
    protected ArrayList<ModificadorAtributo> modificadores = new ArrayList<>();
    protected int mantencion;
    protected int costo;
    protected int dead = -1; // -1: esta vivo, 1-3: muerto pero en terreno, 0: Se debe eliminar del mapa
    protected String rutaSprite1;
    protected String rutaSprite2;
    protected String rutaSpriteMuerto1 = "/images/Tumba1.png";
    protected String rutaSpriteMuerto2 = "/images/Tumba2.png";
    
    
    // CONSTRUCTORES
    public Unidad(){}
    public Unidad(int equipo){
        setDefaults(equipo);
        // Se crea una naturaleza y se agrega a modificadores
        modificadores.add(new ModificadorAtributo());
    }
    
    /**Constructor de unidad, Usada solo por profesor.*/
    public Unidad(int equipo, int puntosCorto, int puntosMedio, String naturaleza1, String naturaleza2){}
    
    
    // METODOS ABSTRACTOS (Distintos en cada unidad)
    /**
     * Setea los valores iniciales correspondiente a cada unidad
     * @param equipo El equipo al cual pertenece la unidad: 1 = equipo azul; 2 = equipo rojo
     */
    protected abstract void setDefaults(int equipo);
    protected abstract void subirNivel();
    
    // METODOS GENERALES (heredables)
    public boolean tieneRango(int rango){
        Iterator<Ataque> iterador = ataques.iterator();
        while(iterador.hasNext()){
            if(iterador.next().getRango() == rango){
                return true;
            }
        }
        return false;
    }
    
    public boolean otorgarExp(int pts){
        this.experiencia += pts;
        if (this.experiencia >= this.expMax){
            this.experiencia -= this.expMax;
            this.subirNivel();
            return true;
        }
        return false;
    }
    
    public boolean recibirDano(int danoRecibido){
        this.hp -= danoRecibido;
        if(this.hp <= 0){
            this.hp=0;
            this.dead = 4;
            return true;
        } 
        return false;
    }
    
    public void restarTurnoMuerto(){
        if(dead > 0){dead--;}
    }
    
    public void curar(int cura){
        this.hp+=cura;
        if(this.hp > this.hpMax){
            this.hp=this.hpMax;
        }
    }
    
    public void restarMod(){
        Iterator<ModificadorAtributo> iterador = modificadores.iterator();
        while(iterador.hasNext()){
            ModificadorAtributo mod = iterador.next();
            if(!mod.reducirTurno()){
                modificadores.remove(mod);
            }
        }
    }
    
    public int porcentajeVida(){return (hp*100)/hpMax;}
    
    public String[] getListaAtaques(){
        ArrayList<String> listaAtaques = new ArrayList<String>();
        
        Iterator<Ataque> iterador = ataques.iterator();
        while(iterador.hasNext()){
            Ataque a = iterador.next();
            listaAtaques.add(a.toString());
        }
        
        return listaAtaques.toArray(new String[0]);
    }
    
    public int[] getAtaque(int rango){
        int[] retorno = null;
        ArrayList<Ataque> listaAtaques = new ArrayList<Ataque>();
        
        Iterator<Ataque> iterador = ataques.iterator();
        while(iterador.hasNext()){
            Ataque a = iterador.next();
            if (a.getRango() == rango){
                listaAtaques.add(a);
            }
        }
        if(!listaAtaques.isEmpty()){
            int random = (new Random()).nextInt(listaAtaques.size());
            retorno = listaAtaques.get(random).toInt();
        }
        return retorno;
    }
    
    public int[] getMejorAtaque(int rango){
        int[] retorno = null;
        Ataque ataque = new Ataque(0,0,1);
        Iterator<Ataque> iterador = ataques.iterator();
        while(iterador.hasNext()){
            Ataque a = iterador.next();
            if (a.getRango() == rango){
                if((a.getDano()*a.getGolpes())>(ataque.getDano()*ataque.getGolpes())){
                    ataque=a;
                }
            }
        }
        if(ataque!=null){
            retorno = ataque.toInt();
        }
        return retorno;
    }
    
    public int[] getOrdenMejoresAtaques(){
        int[] retorno = {1,2,3};
        int[] dano = new int[3];
        
        Iterator<Ataque> iterador = ataques.iterator();
        while(iterador.hasNext()){
            Ataque a = iterador.next();
            if ((a.getDano()*a.getGolpes()) > dano[a.getRango()-1]){
                dano[a.getRango()-1] = (a.getDano()*a.getGolpes());
            }
        }
        for(int i=0;i<3;i++){
            for(int j=0;j<2-i;j++){
                if(dano[j]<dano[j+1]){
                    int aux = dano[j];
                    dano[j]=dano[j+1];
                    dano[j+1] = aux;
                    aux = retorno[j];
                    retorno[j]=retorno[j+1];
                    retorno[j+1] = aux;
                }
            }
        }
        
        return retorno;
    }
    
    public String getSprite(){
        if(dead == -1){
            if (equipo == 1){
                return this.rutaSprite1;
            }
            return this.rutaSprite2;
        }else{
            if (equipo == 1){
                return this.rutaSpriteMuerto1;
            }
            return this.rutaSpriteMuerto2;
        }
    }

    public void setInmovil(boolean flag){inmovil = flag;}
    public int getEquipo() {return this.equipo;}
    public int getMovimientos(){return this.movimiento;}
    public int getCosto(){return this.costo;}
    public int getMantencion(){return this.mantencion;}
    public int getCritMiss(){return this.criticalMiss;}
    public int getExpMuerte(){return (nivel*hpMax/10);}
    public int getHeal(){return heal;}
    public int getNivel(){return nivel;}
    public int getHp(){return hp;}
    public int getDano(){return hpMax-hp;}
    public boolean getInmovil(){return inmovil;}
    public boolean isDead(){return (dead != -1);}
    public boolean isReallyDead(){return (dead == 0);}
    
    
}
