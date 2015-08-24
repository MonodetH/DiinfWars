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
public class Laboratorio extends Casilla {

    public Laboratorio() {
        super();
    }
    public Laboratorio(boolean isHabilitada) {
        super(isHabilitada);
    }
    public Laboratorio(boolean isHorizontal, int parte) {
        super(isHorizontal,parte);
    }
    public Laboratorio(boolean isHorizontal, int parte, boolean isHabilitada) {
        super(isHorizontal,parte,isHabilitada);
    }
    
    @Override
    protected void setDefaults() {
        this.defensa = 70;
        this.rutaSprite = "/images/Laboratorio.jpg";
        this.tipo = "Laboratorio";
    }
    
}
