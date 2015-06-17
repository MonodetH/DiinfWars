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
public class Alumno extends Unidad {
    public static int sCosto = 6;
    public static String sRutaSprite1 = "/images/spriteAlumnoAzul.jpg";
    public static String sRutaSprite2 = "/images/unidadPlaceholder.png";

    public Alumno(int equipo) {
        super(equipo);
    }
    
    @Override
    protected void setDefaults(int equipo) {
        // ATRIBUTOS
        this.equipo = equipo;
        this.hp = 40;
        this.hpMax = 40;
        this.movimiento = 4;
        this.mantencion = 3;
        this.costo = 6;

        this.rutaSprite = (equipo == 1)? Alumno.sRutaSprite1:Alumno.sRutaSprite2;
        
        /*
            La naturaleza se agrega en el constructor de unidad, asi que ya no
            es necesario agregar una denuevo.
        */
        
        // ATAQUES
        this.ataques.add(new Ataque(4,3,2));
        this.ataques.add(new Ataque(4,3,1));
    }
    
}
