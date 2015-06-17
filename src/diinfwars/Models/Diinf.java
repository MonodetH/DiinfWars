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
public class Diinf extends Casilla {

    @Override
    protected void setDefaults() {
        this.defensa = 60;
        this.rutaSprite = "/images/spritePasto.jpg";
    }
    
}
