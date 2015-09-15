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
public class Foro extends Casilla {

    public Foro() {
        super();
    }
    public Foro(boolean isHabilitada) {
        super(isHabilitada);
    }
    public Foro(Edificio edificio) {
        super(edificio);
    }
    public Foro(Edificio edificio,boolean isHabilitada) {
        super(edificio,isHabilitada);
    }
    
    @Override
    protected void setDefaults() {
        this.defensa = 30;
        this.rutaSprite = "/images/Foro.png";
        this.tipo = "Foro";
    }
    
}
