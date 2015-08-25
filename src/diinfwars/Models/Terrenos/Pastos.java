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
public class Pastos extends Casilla {

    public Pastos() {
        super();
    }
    public Pastos(boolean isHabilitada) {
        super(isHabilitada);
    }
    public Pastos(Edificio edificio) {
        super(edificio);
    }
    public Pastos(Edificio edificio,boolean isHabilitada) {
        super(edificio,isHabilitada);
    }
    
    @Override
    protected void setDefaults() {
        this.defensa = 40;
        this.rutaSprite = "/images/Pastos.jpg";
        this.tipo = "Pastos";
    }
    
}
