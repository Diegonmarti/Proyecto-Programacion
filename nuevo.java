/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reservas;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class nuevo {
          String nombre;
    String ruta = "C:\\Users\\juanjose\\Documents\\NetBeansProjects\\3evaluacion\\src\\provicional\\ficheros";
    String prefijo = ".txt";
    String nomfichero="";
    String prefijonom="%N$";
 

    
    public void datos(String nomb) throws IOException {
        cabeza p2=new cabeza(nomb);
        this.nombre=p2.getNom();
        File fichero = new File(this.ruta + this.nombre + this.prefijo);
        PrintWriter salida = new PrintWriter(fichero);
       boolean veri=false;
              if (p2.crear()==true) {
                  System.out.println("fichero creado");
                  veri=true;
                  fichero.createNewFile();
                  
        }else{
                  System.out.println("no creado");
              }
           
              if (veri==true) {
            salida.print(prefijonom+"tutorias de "+this.nombre);
            salida.close();
        }
        }
    }
