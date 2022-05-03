package proyectotercera.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public abstract class FileUtils {
    private static final String RUTA_BASE = "src\\proyectotercera\\salida\\";
    
    public static boolean existe(String nombre) {
        return new File(RUTA_BASE + nombre).exists();
    }
    
    public static void escribirArchivo(String nombre, String texto) {
        PrintWriter pw = null;
        File f = new File(RUTA_BASE + nombre);
        
        if(f.getParentFile() != null && !f.getParentFile().exists()) {
            f.getParentFile().mkdirs();
        }
        
        // Si el archivo no existe, PrintWriter se lo crea
        try {
            pw = new PrintWriter(f.getPath());
        } catch(FileNotFoundException e) {}
        
        pw.print(texto);
        pw.close();
    }
    
    public static void escribirArchivo(String nombre, ISerializable obj) {
        escribirArchivo(nombre, obj.toSerializedData());
    }
    
    public static String leerArchivo(String nombre) {
        Scanner sc = null;
        String datos = "";
        try {
            sc = new Scanner(new File(RUTA_BASE + nombre));
            while(sc.hasNextLine()) { // Mientras que al Scanner le queden lineas por leer, las guardamos en datos
                datos += sc.nextLine()+"\n";
            }
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
