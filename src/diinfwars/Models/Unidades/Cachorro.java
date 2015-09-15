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
public class Cachorro extends Unidad {
    public static int sCosto = 4;
    public static String sRutaSprite1 = "/images/SpriteCachorro.png";
    public static String sRutaSprite2 = "/images/SpriteCachorro2.png";

    public Cachorro(int equipo) {
        super(equipo);
    }
    
    @Override
    protected void setDefaults(int equipo) {
        // ATRIBUTOS
        this.equipo = equipo;
        this.hp = 30;
        this.hpMax = 30;
        this.movimiento = 3;
        this.mantencion = 1;
        this.costo = 4;
        this.expMax = 25;

        this.rutaSprite1 = Cachorro.sRutaSprite1;
        this.rutaSprite2 = Cachorro.sRutaSprite2;
        
        // ATAQUES
        this.ataques.add(new Ataque(1,2,1));
        this.ataques.add(new Ataque(2,1,2));
    }
    
    @Override
    protected void subirNivel() {
        this.nivel++;
        if(nivel==4){expMax+=15;}
        else{this.expMax += 10;}
        if(nivel<5){
            this.hpMax += (hpMax*15/100);
        }else{hpMax += hpMax/10;}
        if(nivel%2 == 1 || nivel == 4){
            modificadores.add(new ModificadorAtributo("dano",1,-1));
        }
        if(nivel%2 == 0 && nivel != 2){
            modificadores.add(new ModificadorAtributo("cantidadGolpes",1,-1));
        }
    }
    
}