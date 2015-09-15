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
public class AlumnoNivelSuperior extends Unidad {
    public static int sCosto = 8;
    public static String sRutaSprite1 = "/images/SpriteAlumnoSuperior.png";
    public static String sRutaSprite2 = "/images/SpriteAlumnoSuperior2.png";

    public AlumnoNivelSuperior(int equipo) {
        super(equipo);
    }
    
    @Override
    protected void setDefaults(int equipo) {
        // ATRIBUTOS
        this.equipo = equipo;
        this.hp = 40;
        this.hpMax = 40;
        this.movimiento = 6;
        this.mantencion = 4;
        this.costo = 8;
        this.expMax= 35;

        this.rutaSprite1 = AlumnoNivelSuperior.sRutaSprite1;
        this.rutaSprite2 = AlumnoNivelSuperior.sRutaSprite2;
        
        // ATAQUES
        this.ataques.add(new Ataque(2,2,1));
        this.ataques.add(new Ataque(1,3,2));
        this.ataques.add(new Ataque(1,2,3));
    }
    
    @Override
    protected void subirNivel() {
        this.nivel++;
        if (nivel == 2){expMax += 15;}
        else {expMax+=10;}
        this.hpMax += (hpMax*15/100);
        if(nivel%2 == 1 ){
            modificadores.add(new ModificadorAtributo("dano",1,-1));
        }
        if(nivel%2 == 0 && nivel != 2){
            modificadores.add(new ModificadorAtributo("cantidadGolpes",1,-1));
        }
    }
    
}