/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diinfwars;

import diinfwars.Controllers.CPrincipal;
import diinfwars.Controllers.CLogin;

import diinfwars.Models.Registro;
import java.util.ArrayList;

/**
 *
 * @author MonodetH
 */
public class DiinfWars {
    
    private static CPrincipal sCPrincipal;
    private static CLogin sCLogin;
    
    
    public static void destroyController(String controller){
        if (controller == "Principal"){sCPrincipal = null;}
        else if(controller == "Login"){sCLogin = null;}
    }
    
    public static CPrincipal getCPrincipal(){
        if(sCPrincipal == null){sCPrincipal =new CPrincipal();}
        return sCPrincipal;
    }
    public static CLogin getCLogin(){
        if(sCLogin == null){sCLogin =new CLogin();}
        return sCLogin;
    }
    
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        getCLogin().logearJugador(1); //Logea a la CPU
        getCPrincipal().run();
    }
    
}
