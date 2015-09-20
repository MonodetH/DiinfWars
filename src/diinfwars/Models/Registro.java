/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diinfwars.Models;

import java.awt.event.ActionEvent;
import java.io.*;
import java.util.ArrayList;
import javax.swing.JButton;


/**
 *
 * @author MonodetH
 */
public class Registro {
    // Atributos
    private ArrayList<String> turnoAnterior;
    private ArrayList<String> turnoActual;
    private File appLog;
    private File estadisticas;
    private File batallas;
    
 
    public Registro(String ganador, String perdedor) throws IOException{
        File uno = new File("C:\\Users\\Zen\\Documents\\NetBeansProjects\\DiinfWars\\src\\logs\\log.txt");
        modificarArchivoGanador(ganador, uno);
        modificarArchivoPerdedor(perdedor,uno);
    }
    
    public void escribirGanador(String linea,String ganador,int valor, File f) throws IOException{
        if (!f.exists()){
            f.createNewFile();
        }
        FileWriter fw = new FileWriter(f,true);
        BufferedWriter bw =new BufferedWriter(fw);
        PrintWriter pw = new PrintWriter(bw);
        if(valor == 0){
            String[] nuevo = linea.split("---");
            pw.println(nuevo[0]+"---"+(Integer.parseInt(nuevo[1])+1)+"---"+nuevo[2]);
            System.out.println(nuevo[0]+"---"+(Integer.parseInt(nuevo[1])+1)+"---"+nuevo[2]);
            pw.close();
            bw.close();
            fw.close();
        }
        else{
            pw.println(ganador+"---"+1+"---"+0);
            pw.close();
            bw.close();
            fw.close();
        }
    }
    
    public void escribirPerdedor(String linea,String perdedor,int valor,File f) throws IOException{
        if(!f.exists()){
            f.createNewFile();
        }
        FileWriter fw2 = new FileWriter(f,true);
        BufferedWriter bw2 =new BufferedWriter(fw2);
        PrintWriter pw2 = new PrintWriter(bw2);
        if(valor == 0){
            String[] nuevo = linea.split("---");
            pw2.println(nuevo[0]+"---"+nuevo[1]+"---"+(Integer.parseInt(nuevo[2])+1));
            System.out.println(nuevo[0]+"---"+nuevo[1]+"---"+(Integer.parseInt(nuevo[2])+1));
            pw2.close();
            bw2.close();
            fw2.close();
        }
        else{
            pw2.println(perdedor+"---"+0+"---"+1);
            pw2.close();
            bw2.close();
            fw2.close();
        }
    }
    
    public void escribirResto(String linea,String ganador,File f) throws IOException{
        if (!f.exists()){
            f.createNewFile();
        }
        FileWriter fw = new FileWriter(f,true);
        BufferedWriter bw =new BufferedWriter(fw);
        PrintWriter pw = new PrintWriter(bw);
        pw.println(linea);
        pw.close();
        bw.close();
        fw.close();
    }
    
    public void modificarArchivoPerdedor(String perdedor,File f1) throws FileNotFoundException, IOException{
        File log2 = new File("C:\\Users\\Zen\\Documents\\NetBeansProjects\\DiinfWars\\src\\logs\\log2.txt");
        FileReader fr1 = new FileReader(f1);
        BufferedReader br = new BufferedReader(fr1);
        String linea;
        String linea2;
        int contador = 0;
        
        System.out.println("QUE MIERDA PASA");
        
            //Poner al ganador y perdedor
            while((linea=br.readLine())!= null){
                System.out.println("Hay algo en el archivo");
                System.out.println("----------------------"+linea+"----------------------");
                String[] verificador = linea.split("---");
                //Si esta el ganador
                if(verificador[0].equals(perdedor)){
                    System.out.println("Esta el ganador: "+perdedor+"/"+verificador[0]);
                    escribirPerdedor(linea,perdedor,0,log2);
                    contador++;
                    }
                //Paso al resto al otro archivo
                else {
                    escribirResto(linea,perdedor,log2);
                    System.out.println("Escribir el resto del archivo");}
                }
                if(br.readLine()== null && contador == 0){
                    escribirPerdedor(linea, perdedor, 1, log2);
                }
        br.close();
        String nombre= f1.getParent()+"\\"+f1.getName();
        try {
         /*Si existe el fichero*/
         if(f1.exists()){
           /*Borra el fichero*/
           f1.delete();
           System.out.println("Fichero Borrado con Exito");
         }
     } catch (Exception ex) {
         /*Captura un posible error y le imprime en pantalla*/
          System.out.println(ex.getMessage());
     }
        log2.renameTo(f1);

    }

    public void modificarArchivoGanador(String ganador,File f1) throws FileNotFoundException, IOException{
        File log2 = new File("C:\\Users\\Zen\\Documents\\NetBeansProjects\\DiinfWars\\src\\logs\\log2.txt");
        FileReader fr1 = new FileReader(f1);
        BufferedReader br = new BufferedReader(fr1);
        String linea;
        String linea2;
        int contador = 0;
        
        System.out.println("QUE MIERDA PASA");
        
            //Poner al ganador y perdedor
            while((linea=br.readLine())!= null){
                System.out.println("Hay algo en el archivo");
                System.out.println("----------------------"+linea+"----------------------");
                String[] verificador = linea.split("---");
                //Si esta el ganador
                if(verificador[0].equals(ganador)){
                    System.out.println("Esta el ganador: "+ganador+"/"+verificador[0]);
                    escribirGanador(linea,ganador,0,log2);
                    contador++;
                    }
//                else if(){}
                //Paso al resto al otro archivo
                else {
                    escribirResto(linea,ganador,log2);
                    
                    System.out.println("Escribir el resto del archivo");}
                }
                if(br.readLine()== null && contador == 0){
                    escribirGanador(linea, ganador, 1, log2);
                }
        br.close();
        String nombre= f1.getParent()+"\\"+f1.getName();
        try {
            /*Si existe el fichero*/
            if(f1.exists()){
               /*Borra el fichero*/
                f1.delete();
                System.out.println("Fichero Borrado con Exito");
          }
        } catch (Exception ex) {
            /*Captura un posible error y le imprime en pantalla*/
            System.out.println(ex.getMessage());
        }
        log2.renameTo(f1);
    }
    }
