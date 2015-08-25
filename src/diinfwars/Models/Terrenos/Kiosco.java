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
public class Kiosco extends Casilla {

    public Kiosco() {
        super();
    }
    public Kiosco(boolean isHabilitada) {
        super(isHabilitada);
    }
    public Kiosco(Edificio edificio) {
        super(edificio);
    }
    public Kiosco(Edificio edificio,boolean isHabilitada) {
        super(edificio,isHabilitada);
    }
    
    @Override
    protected void setDefaults() {
        this.defensa = 60;
        this.rutaSprite = "/images/Kiosko.png";
        this.tipo = "Kiosco";
    }
    
}
