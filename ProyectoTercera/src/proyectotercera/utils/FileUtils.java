package proyectotercera.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public abstract class FileUtils {
    private static final String RUTA_BASE = "src\\proyectotercera\\salida\\";
    
    public static boolean escribirArchivo(String nombre, String texto) {
        PrintWriter pw = null;
        File f;
        
        f = new File(RUTA_BASE + nombre);
        if(f.getParentFile() != null && !f.getParentFile().exists()) {
            f.getParentFile().mkdirs();
        }
        
        try {
            pw = new PrintWriter(f.getPath());
        } catch(FileNotFoundException e) {}
        
        pw.print(texto);
        pw.close();
        
        if(pw != null) pw.close();
        return true;
    }
    
    public static boolean escribirArchivo(String nombre, ISerializable obj) {
        return escribirArchivo(nombre, obj.toSerializedData());
    }
    
    public static String leerArchivo(String nombre) {
        Scanner sc = null;
        String datos = "";
        try {
            sc = new Scanner(new File(RUTA_BASE + nombre));
            while(sc.hasNextLine()) {
                datos += sc.nextLine()+"\n";
            }
            sc.close();
            return datos;
        } catch(FileNotFoundException e) {
            return null;
        } finally{
            if(sc != null) sc.close();
        }
    }
    
    public static void parsearArchivo(String nombre, ISerializable outObj) {
        outObj.fromSerializedData(leerArchivo(nombre));
    }
}