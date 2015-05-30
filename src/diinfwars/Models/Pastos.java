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
public class Pastos extends Casilla{
    
    /*
     * Deberia utilizar los constructores de la clase padre Casilla
     */
    
    @Override
    protected void setDefaults() {
        this.defensa = 40;
        this.rutaSprite = "/images/spritePasto.jpg";
    }
}
