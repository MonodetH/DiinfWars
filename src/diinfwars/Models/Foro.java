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
public class Foro extends Casilla {

    public Foro() {
        super();
    }
    public Foro(boolean isHabilitada) {
        super(isHabilitada);
    }
    public Foro(boolean isHorizontal, int parte) {
        super(isHorizontal,parte);
    }
    public Foro(boolean isHorizontal, int parte, boolean isHabilitada) {
        super(isHorizontal,parte,isHabilitada);
    }
    
    @Override
    protected void setDefaults() {
        this.defensa = 30;
        this.rutaSprite = "/images/spritePasto.jpg";
    }
    
}
