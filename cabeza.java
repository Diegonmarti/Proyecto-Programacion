/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reservas;

import java.util.Scanner;

/**
 *
 * @author juanjose
 */
public class cabeza {
    String nom;
    Scanner sc=new Scanner(System.in);

    public cabeza(String nombre) {
        this.nom= nombre;
        crear();
    }
     
    public boolean crear(){
        boolean verificador=false;
        int aux=0;
        String sol=nom;
       
        for (int i = 0; i < nom.length(); i++) {
            if (nom.charAt(i)>=65&& nom.charAt(i)<=90||nom.charAt(i)>=97&& nom.charAt(i)<=122) {
                  aux++;             
            }
            
        }if (aux==nom.length()) {
                verificador=true;
                System.out.println("si");
            }else{
                System.out.println("error tienes un caracter no valido");
                nom=sc.nextLine();
            }
        
        return verificador;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Scanner getSc() {
        return sc;
    }

    public void setSc(Scanner sc) {
        this.sc = sc;
    }

   
     
}
