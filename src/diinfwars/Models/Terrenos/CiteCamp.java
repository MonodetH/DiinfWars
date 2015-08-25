/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diinfwars.Models.Terrenos;

import diinfwars.Models.Casilla;
import diinfwars.Models.Edificio;

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
    public CiteCamp(Edificio edificio) {
        super(edificio);
    }
    public CiteCamp(Edificio edificio,boolean isHabilitada) {
        super(edificio,isHabilitada);
    }
    
    @Override
    protected void setDefaults() {
        this.defensa = 50;
        this.rutaSprite = "/images/CiteCamp.png";
        this.tipo = "CiteCamp";
    }
    
}
