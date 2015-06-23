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
public class Sherwood extends Casilla {

    public Sherwood() {
        super();
    }
    public Sherwood(boolean isHabilitada) {
        super(isHabilitada);
    }
    public Sherwood(boolean isHorizontal, int parte) {
        super(isHorizontal,parte);
    }
    public Sherwood(boolean isHorizontal, int parte, boolean isHabilitada) {
        super(isHorizontal,parte,isHabilitada);
    }
     
    @Override
    protected void setDefaults() {
        this.defensa = 100;
        this.rutaSprite = "/images/Sherwood.jpg";
    }
    
}
