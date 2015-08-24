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
public class CiteCamp extends Casilla {
    
    public CiteCamp() {
        super();
    }
    public CiteCamp(boolean isHabilitada) {
        super(isHabilitada);
    }
    public CiteCamp(boolean isHorizontal, int parte) {
        super(isHorizontal,parte);
    }
    public CiteCamp(boolean isHorizontal, int parte, boolean isHabilitada) {
        super(isHorizontal,parte,isHabilitada);
    }
    
    @Override
    protected void setDefaults() {
        this.defensa = 50;
        this.rutaSprite = "/images/CiteCamp.png";
        this.tipo = "CiteCamp";
    }
    
}
