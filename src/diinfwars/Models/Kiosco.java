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
public class Kiosco extends Casilla {

    public Kiosco() {
        super();
    }
    public Kiosco(boolean isHabilitada) {
        super(isHabilitada);
    }
    public Kiosco(boolean isHorizontal, int parte) {
        super(isHorizontal,parte);
    }
    public Kiosco(boolean isHorizontal, int parte, boolean isHabilitada) {
        super(isHorizontal,parte,isHabilitada);
    }
    
    @Override
    protected void setDefaults() {
        this.defensa = 60;
        this.rutaSprite = "/images/spritePasto.jpg";
    }
    
}
