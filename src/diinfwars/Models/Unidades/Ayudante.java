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
public class Ayudante extends Unidad {
    public static int sCosto = 8;
    public static String sRutaSprite1 = "/images/SpriteAyudante.png";
    public static String sRutaSprite2 = "/images/SpriteAyudante2.png";

    public Ayudante(int equipo) {
        super(equipo);
    }
    
    @Override
    protected void setDefaults(int equipo) {
        // ATRIBUTOS
        this.equipo = equipo;
        this.hp = 50;
        this.hpMax = 50;
        this.movimiento = 3;
        this.mantencion = 4;
        this.costo = 8;
        this.expMax = 40;

        this.rutaSprite1 = Ayudante.sRutaSprite1;
        this.rutaSprite2 = Ayudante.sRutaSprite2;
        
        // ATAQUES
        this.ataques.add(new Ataque(7,3,1));
        this.ataques.add(new Ataque(9,2,1));
    }
    
    @Override
    protected void subirNivel() {
        this.nivel++;
        this.expMax += 10;
        if(nivel < 5){hpMax += (hpMax*15/100);}
        else{this.hpMax += hpMax/10;}
        if(nivel%2 == 1 || nivel == 4){
            modificadores.add(new ModificadorAtributo("dano",1,-1));
        }
        if(nivel%2 == 0 && nivel != 2){
            modificadores.add(new ModificadorAtributo("cantidadGolpes",1,-1));
        }
    }
}