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
public class Alumno extends Unidad{
    public static int sCosto = 6;
    public static String sRutaSprite1 = "/images/spriteAlumnoAzul.jpg";
    public static String sRutaSprite2 = "/images/unidadPlaceholder.png";

    public Alumno(int equipo) {
        super(equipo);
    }
    
    @Override
    protected void setDefaults(int equipo) {
        // ATRIBUTOS
        this.hp = 40;
        this.hpMax = 40;
        this.movimiento = 4;
        this.mantencion = 3;
        this.costo = 6;
        
        /*
            Aqui depende del equipo al que pertenezca si se utilizará el sprite
            azul o rojo.
            CAMBIAR LA RUTA DEL ROJO (SEGUNDO STRING) CUANDO SE TENGA EL SPRITE
        */
        this.rutaSprite = (equipo == 1)? "/images/spriteAlumnoAzul.jpg":"/images/unidadPlaceholder.png";
        
        // ATAQUES
        this.ataques.add(new Ataque(4,3,2));
        this.ataques.add(new Ataque(4,3,1));
    }
    
}
