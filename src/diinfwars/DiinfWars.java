/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diinfwars;

import diinfwars.Controllers.CPrincipal;

import diinfwars.Models.Registro;
import java.util.ArrayList;

/**
 *
 * @author MonodetH
 */
public class DiinfWars {
    
    private static CPrincipal sCPrincipal;
    
    
    public static void destroyController(String controller){
        if (controller == "Principal"){sCPrincipal = null;}
    }
    
    public static CPrincipal getCPrincipal(){
        if(sCPrincipal == null){sCPrincipal =new CPrincipal();}
        return sCPrincipal;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        getCPrincipal().run();
    }
    
}
