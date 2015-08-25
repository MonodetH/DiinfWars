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
public class Casitas extends Casilla {

    public Casitas() {
        super();
    }
    public Casitas(boolean isHabilitada) {
        super(isHabilitada);
    }
    public Casitas(Edificio edificio) {
        super(edificio);
    }
    public Casitas(Edificio edificio,boolean isHabilitada) {
        super(edificio,isHabilitada);
    }
    
    @Override
    protected void setDefaults() {
        this.defensa = 40;
        this.rutaSprite = "/images/Casitas.jpg";
        this.tipo = "Casitas";
    }
    
}
