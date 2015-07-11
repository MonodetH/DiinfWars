/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diinfwars;

import diinfwars.Controllers.CEnfrentamiento;
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
    private static Registro sRegistro;
    
    public static CPrincipal getCPrincipal(){return sCPrincipal;}
    public static CLogin getCLogin(){return sCLogin;}
    public static Registro getRegistro(){return sRegistro;}
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        sRegistro = new Registro();
        sCLogin = new CLogin();
        sCLogin.logearJugador(1); //Logea a la CPU
        sCPrincipal = new CPrincipal();
    }
    
}
