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
public class Calle extends Casilla {

    public Calle() {
        super();
    }
    public Calle(boolean isHabilitada) {
        super(isHabilitada);
    }
    public Calle(boolean isHorizontal, int parte) {
        super(isHorizontal,parte);
    }
    public Calle(boolean isHorizontal, int parte, boolean isHabilitada) {
        super(isHorizontal,parte,isHabilitada);
    }
    
    @Override
    protected void setDefaults() {
        this.defensa = 0;
        this.rutaSprite = "/images/spritePasto.jpg";
    }
    
}
