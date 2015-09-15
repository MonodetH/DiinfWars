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
public class Alumno extends Unidad {
    public static int sCosto = 6;
    public static String sRutaSprite1 = "/images/SpriteAlumno.png";
    public static String sRutaSprite2 = "/images/SpriteAlumno2.png";

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
        this.expMax = 35;

        this.rutaSprite1 = Alumno.sRutaSprite1;
        this.rutaSprite2 = Alumno.sRutaSprite2;
        
        /*
            La naturaleza se agrega en el constructor de unidad, asi que ya no
            es necesario agregar una denuevo.
        */
        
        // ATAQUES
        this.ataques.add(new Ataque(4,3,2));
        this.ataques.add(new Ataque(4,3,1));
    }
    
    @Override
    protected void subirNivel() {
        this.nivel++;
        expMax += 10;
        
        this.hpMax += (hpMax/5);
        if(nivel%2 == 1 ){
            modificadores.add(new ModificadorAtributo("dano",1,-1));
        }
        if(nivel%2 == 0 && nivel != 2){
            modificadores.add(new ModificadorAtributo("cantidadGolpes",1,-1));
        }
    }
}
