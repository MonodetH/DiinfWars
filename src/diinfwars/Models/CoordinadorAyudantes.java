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
public class CoordinadorAyudantes extends Unidad {
    public static int sCosto = 10;
    public static String sRutaSprite1 = "/images/SpriteCoordinador.png";
    public static String sRutaSprite2 = "/images/SpriteCoordinador2.png";

    public CoordinadorAyudantes(int equipo) {
        super(equipo);
    }
    
    @Override
    protected void setDefaults(int equipo) {
        // ATRIBUTOS
        this.equipo = equipo;
        this.hp = 35;
        this.hpMax = 35;
        this.movimiento = 2;
        this.mantencion = 5;
        this.costo = 10;

        this.rutaSprite1 = CoordinadorAyudantes.sRutaSprite1;
        this.rutaSprite2 = CoordinadorAyudantes.sRutaSprite2;
        
        // ATAQUES
        this.ataques.add(new Ataque(6,3,3));
        this.ataques.add(new Ataque(2,2,1));
    }
    
}