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

        this.rutaSprite = (equipo == 1)? Cachorro.sRutaSprite1:Cachorro.sRutaSprite2;
        
        // ATAQUES
        this.ataques.add(new Ataque(1,2,1));
        this.ataques.add(new Ataque(2,1,2));
    }
    
}