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
public class Pastos extends Casilla {

    public Pastos() {
        super();
    }
    public Pastos(boolean isHabilitada) {
        super(isHabilitada);
    }
    public Pastos(boolean isHorizontal, int parte) {
        super(isHorizontal,parte);
    }
    public Pastos(boolean isHorizontal, int parte, boolean isHabilitada) {
        super(isHorizontal,parte,isHabilitada);
    }
    
    @Override
    protected void setDefaults() {
        this.defensa = 40;
        this.rutaSprite = "/images/Pastos.jpg";
    }
    
}
