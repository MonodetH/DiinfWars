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
public class EAO extends Casilla {

    public EAO() {
        super();
    }
    public EAO(boolean isHabilitada) {
        super(isHabilitada);
    }
    public EAO(Edificio edificio) {
        super(edificio);
    }
    public EAO(Edificio edificio,boolean isHabilitada) {
        super(edificio,isHabilitada);
    }
    
    @Override
    protected void setDefaults() {
        this.defensa = 50;
        this.rutaSprite = "/images/EAO.jpg";
        this.tipo = "EAO";
    }
    
}
