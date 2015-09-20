/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diinfwars.Controllers;

import diinfwars.Views.VEstadisticas;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.io.File;
import java.io.FileReader;
import javax.swing.JButton;

/**
 *
 * @author Zen
 */
public class CEstadisticas implements ActionListener {
    private CPrincipal p;
    private CEnfrentamiento e;
    
    /**Instancia de la vista principal*/
    private static VEstadisticas v;
    
    public CEstadisticas(CPrincipal padre){
        p = padre;
    }
    
    public void run() throws IOException{
        if (this.v == null){this.v = new VEstadisticas(this);}
        this.v.setVisible(true);
        File uno = new File("C:\\Users\\Zen\\Documents\\NetBeansProjects\\DiinfWars\\src\\logs\\log.txt");
        escritor(lector(uno));;
    }
 
    public String lector(File archivo) throws FileNotFoundException, IOException{
        FileReader fr = new FileReader(archivo);
        BufferedReader br = new BufferedReader(fr);
        String contenido="";
        String linea;
        while((linea=br.readLine())!=null){
            contenido +=linea+"\n";
        }
        br.close();
        fr.close();
        return contenido;
    }
    
    public void escritor(String texto){v.getPanel().setText(texto);}
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        // En caso de que sean botones
        if( source instanceof JButton){
            JButton boton = (JButton) e.getSource();
            if (boton == v.getBVolver()){
                v.dispose();
                p.run();
            }
        }
    }
}

