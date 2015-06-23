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
public class EAO extends Casilla {

    public EAO() {
        super();
    }
    public EAO(boolean isHabilitada) {
        super(isHabilitada);
    }
    public EAO(boolean isHorizontal, int parte) {
        super(isHorizontal,parte);
    }
    public EAO(boolean isHorizontal, int parte, boolean isHabilitada) {
        super(isHorizontal,parte,isHabilitada);
    }
    
    @Override
    protected void setDefaults() {
        this.defensa = 50;
        this.rutaSprite = "/images/EAO.jpg";
    }
    
}
