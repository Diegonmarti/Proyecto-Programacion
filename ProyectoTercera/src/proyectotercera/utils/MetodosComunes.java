package proyectotercera.utils;

import java.util.Scanner;

public abstract class MetodosComunes {
    private static Scanner entrada = new Scanner(System.in);
    public static void guardarArchivo(ISerializable obj, String mensaje) {
        String nombre = "";
        boolean fin = false;
        boolean primeraIteracion = true;
        
        while (!fin) {
            // para que ponga otro mensaje si se vuelve a preguntar (si falla o si no sobreescribe)
            if(!primeraIteracion) {
                System.out.println(mensaje);
            }
            primeraIteracion = false;
            nombre = entrada.nextLine().trim(); //meter el nombre del fichero
            if(nombre.length() > 0) {  //Al menos meter un carácter
                /* explicacion regex
                    .* -> cualquier caracter (0 + veces)
                    [(...)] -> cualquiera de estos caracteres:
                        \\ -> "\"
                        /
                        :
                        \* -> "*"
                        \? -> "?"
                        \" -> el caracter "
                        <
                        >
                        \| -> "|"
                    .* -> cualquier caracter (0 + veces)
                */
                if(nombre.matches(".*[\\\\/:\\*\\?\"<>\\|].*")){ //que no meta ninguno de estos símbolos
                    System.out.println("ERROR: Nombre de archivo no válido.");
                }else {  //si lo mete todo bien, puede salir
                    if(!nombre.endsWith(".txt")) {
                        nombre += ".txt";
                    }

                    if(FileUtils.existe(nombre)) {//si colocas el nombre de un archivo que ya existe 
                        System.out.println("El fichero " + nombre + " ya existe, si desea sobreescribirlo, introduzca SI.");
                        String opcion = entrada.nextLine();

                        if(opcion.equalsIgnoreCase("SI")) {
                            System.out.println("Sobrescribiendo...");  
                        }else {
                            continue;
                        }
                    }
                    fin = true;
                }
            } else {
                System.out.println("ERROR: Introduce un nombre de archivo.");
            }
        }
        FileUtils.escribirArchivo(nombre, obj); // llamamos a la funcion escribir archivo para escribir en un fichero
    }
}
