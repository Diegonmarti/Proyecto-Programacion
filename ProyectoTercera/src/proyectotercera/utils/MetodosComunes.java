package proyectotercera.utils;

import java.util.Scanner;

import proyectotercera.Config;

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
                    // si el nombre contiene puntos, cambiarlos por "-"
                    if(nombre.contains(".")) {
                        System.out.println("Aviso, tu fichero contiene puntos (\".\") en el nombre, se van a reemplazar por guiones (\"-\").");
                        nombre = nombre.substring(0, nombre.length() - 4).replaceAll("\\.", "-") + nombre.substring(nombre.length() - 4);
                    }
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

    // Llamar siempre al principio de los mains. Se encarga de cargar el archivo de configuración y crearlo si no existe
    public static void cargarConfiguracion() {
        String configPath = Config.getConfigFilePath();
        if(FileUtils.existe(configPath)) {
            FileUtils.parsearArchivo(configPath, Config.INSTANCIA);
        }else{
            FileUtils.escribirArchivo(configPath, Config.INSTANCIA);
        }
    }

    public static boolean conectarDB() {
        MetodosComunes.cargarConfiguracion();
        DBUtils.initParams(Config.getAddress(), Config.getDBName(), Config.getUsername(), Config.getPassword());

        boolean conectado = DBUtils.connect(true);
        if(conectado) {
            DBUtils.executeSQLFile(Config.getSqlPath());
        }

        return conectado;
    }

    public static String pedirNombre(String mensaje) {
        String input;
        while (true) {
            System.out.print(mensaje);
            input = entrada.nextLine().trim();
            if(input.length() > 0) {
                return input;
            } else {
                System.out.println("ERROR: No puede dejar el nombre en blanco.");
            }
        }
    }

    public static boolean checkTelefono(String telefono) {
        int tlf;
        try {
            tlf = Integer.parseInt(telefono);
            if(tlf > 0 && telefono.length() == 9) {
                return true;
            }
        }catch(NumberFormatException e) {}
        return false;
    }

    public static int pedirTelefono(String mensaje) {
        String input;
        while (true) {
            System.out.print(mensaje);
            input = entrada.nextLine().trim();
            if(input.length() > 0) {
                if(checkTelefono(input)) {
                    return Integer.parseInt(input);
                }else {
                    System.out.println("ERROR: Introduzca un telefono valido.");
                }
            } else {
                System.out.println("ERROR: No puede dejar el telefono en blanco.");
            }
        }
    }

    public static boolean checkEmail(String email) {
        return email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
    }

    public static String pedirEmail(String mensaje) {
        String input;
        while (true) {
            System.out.print(mensaje);
            input = entrada.nextLine().trim();
            if(input.length() > 0) {
                if(checkEmail(input)) {
                    return input;
                }else {
                    System.out.println("ERROR: Introduzca una dirección de e-mail valida.");
                }
            } else {
                System.out.println("ERROR: No puede dejar el e-mail en blanco.");
            }
        }
    }

    // No se usa el trim() porque los espacios suelen importar en las contraseñas
    public static String pedirPassword(String mensaje, String mensajeRepetir, boolean repetir) {
        String input = "";
        String confirmacion = "";
        boolean fin = false;

        while (!fin) {
            System.out.print(mensaje);
            input = entrada.nextLine();
            if(input.length() > 0) {
                if(input.length() >= 8) {
                    if(repetir) {
                        // Pedir otra vez la contraseña para confirmar. Si coinciden, se termina, y si no, se vuelve a empezar
                        confirmacion = pedirPassword(mensajeRepetir, "", false);
                        if(input.equals(confirmacion)) {
                            fin = true;
                        }else {
                            System.out.println("ERROR: Las contraseñas no coinciden. Por favor vuelva a introducir una contraseña.");
                        }
                    }else {
                        fin = true;
                    }
                }else {
                    System.out.println("ERROR: Introduzca una contraseña válida.");
                    System.out.println("Requisitos: Mínimo 8 caractéres."); 
                }
            } else {
                System.out.println("ERROR: No puede dejar la contraseña en blanco.");
            }
        }

        return input;
    }

    public static boolean pedirConfirmacion(String mensaje) {
        String input;

        while(true) {
            System.out.println(mensaje);
            input = entrada.nextLine().trim();
            if(input.length() > 0) {   //Si ha metido un valor
                if(input.equalsIgnoreCase("SI")) {
                    return true;
                }else if(input.equalsIgnoreCase("NO")) {
                    return false;
                }else {
                    System.out.println("ERROR: Introduce SI o NO.");
                }
            } else {
                System.out.println("ERROR: Entrada vacia. Introduce SI o NO.");
            }
        }   
    }
}
