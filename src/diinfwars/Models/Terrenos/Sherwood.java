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
public class Sherwood extends Casilla {

    public Sherwood() {
        super();
    }
    public Sherwood(boolean isHabilitada) {
        super(isHabilitada);
    }
    public Sherwood(Edificio edificio) {
        super(edificio);
    }
    public Sherwood(Edificio edificio,boolean isHabilitada) {
        super(edificio,isHabilitada);
    }
     
    @Override
    protected void setDefaults() {
        this.defensa = 100;
        this.rutaSprite = "/images/Sherwood.jpg";
        this.habilitada = false;
        this.tipo = "Sherwood";
    }
    
}
