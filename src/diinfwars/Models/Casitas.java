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
public class Casitas extends Casilla {

    public Casitas() {
        super();
    }
    public Casitas(boolean isHabilitada) {
        super(isHabilitada);
    }
    public Casitas(boolean isHorizontal, int parte) {
        super(isHorizontal,parte);
    }
    public Casitas(boolean isHorizontal, int parte, boolean isHabilitada) {
        super(isHorizontal,parte,isHabilitada);
    }
    
    @Override
    protected void setDefaults() {
        this.defensa = 40;
        this.rutaSprite = "/images/Casitas.jpg";
    }
    
}
