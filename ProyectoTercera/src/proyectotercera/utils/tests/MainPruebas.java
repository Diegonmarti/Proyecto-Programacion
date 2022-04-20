package proyectotercera.utils.tests;

import proyectotercera.utils.FileUtils;

public class MainPruebas {
    public static void main(String[] args) {
        // Escribir datos
        //FileUtils.escribirArchivo("clasea.txt", new ClaseA((byte)9, "Pepe", "pepe@pe.pe"));
       
        // Leer datos
        ClaseA c = new ClaseA();
        FileUtils.parsearArchivo("clasea.txt", c);
        System.out.println(c);
    }
}
