/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diinfwars.Models.Unidades;

import diinfwars.Models.Ataque;
import diinfwars.Models.Unidad;

/**
 *
 * @author MonodetH
 */
public class Pame extends Unidad {
    public static int sCosto = 12;
    public static String sRutaSprite1 = "/images/SpritePame.png";
    public static String sRutaSprite2 = "/images/SpritePame2.png";

    public Pame(int equipo) {
        super(equipo);
    }
    
    @Override
    protected void setDefaults(int equipo) {
        // ATRIBUTOS
        this.equipo = equipo;
        this.hp = 30;
        this.hpMax = 30;
        this.movimiento = 4;
        this.mantencion = 6;
        this.costo = 12;

        this.rutaSprite1 = Pame.sRutaSprite1;
        this.rutaSprite2 = Pame.sRutaSprite2;
        
        // ATAQUES
        this.ataques.add(new Ataque(3,4,2));
    }
    
}