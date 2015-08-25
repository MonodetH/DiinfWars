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
public class Laboratorio extends Casilla {

    public Laboratorio() {
        super();
    }
    public Laboratorio(boolean isHabilitada) {
        super(isHabilitada);
    }
    public Laboratorio(Edificio edificio) {
        super(edificio);
    }
    public Laboratorio(Edificio edificio,boolean isHabilitada) {
        super(edificio,isHabilitada);
    }
    
    @Override
    protected void setDefaults() {
        this.defensa = 70;
        this.rutaSprite = "/images/Laboratorio.jpg";
        this.tipo = "Laboratorio";
    }
    
}
