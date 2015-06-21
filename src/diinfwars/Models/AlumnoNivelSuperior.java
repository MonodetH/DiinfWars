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
public class AlumnoNivelSuperior extends Unidad {
    public static int sCosto = 8;
    public static String sRutaSprite1 = "/images/unidadPlaceholder.png";
    public static String sRutaSprite2 = "/images/unidadPlaceholder.png";

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

        this.rutaSprite = (equipo == 1)? AlumnoNivelSuperior.sRutaSprite1:AlumnoNivelSuperior.sRutaSprite2;
        
        // ATAQUES
        this.ataques.add(new Ataque(2,2,1));
        this.ataques.add(new Ataque(1,3,2));
        this.ataques.add(new Ataque(1,2,3));
    }
    
}