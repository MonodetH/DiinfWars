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
public class Ayudante extends Unidad {
    public static int sCosto = 8;
    public static String sRutaSprite1 = "/images/unidadPlaceholder.png";
    public static String sRutaSprite2 = "/images/unidadPlaceholder.png";

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

        this.rutaSprite = (equipo == 1)? Ayudante.sRutaSprite1:Ayudante.sRutaSprite2;
        
                // ATAQUES
        this.ataques.add(new Ataque(7,3,1));
        this.ataques.add(new Ataque(9,2,1));
    }
    
}