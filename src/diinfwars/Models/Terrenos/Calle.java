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
public class Calle extends Casilla {

    public Calle() {
        super();
    }
    public Calle(boolean isHabilitada) {
        super(isHabilitada);
    }
    public Calle(Edificio edificio) {
        super(edificio);
    }
    public Calle(Edificio edificio,boolean isHabilitada) {
        super(edificio,isHabilitada);
    }
    
    @Override
    protected void setDefaults() {
        this.defensa = 20;
        this.rutaSprite = "/images/Calle.png";
        this.tipo = "Calle";
    }
    
}
