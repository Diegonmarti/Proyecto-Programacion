package proyectotercera;

import java.util.Date;
import java.util.ArrayList;
import java.util.Scanner;

import proyectotercera.utils.FileUtils;
import proyectotercera.utils.MetodosComunes;


// Pendiende Ampliacion: Mas de un alumno/una reserva por hora
public class Reservar {

    //private static Reservar[] cita;
    private static Scanner entrada;

    private static Reservas horario;

    public static void main(String[] args) {
        entrada = new Scanner(System.in);
        horario = new Reservas();
        Reservar cita = new Reservar();
           
        
        String palabra;
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        System.out.println("> Programa de reservas de Tutorías >");
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        System.out.println();
        //Hacer el Scanner que llame al fichero
        pedirArchivo();

        do {
            System.out.print("Indica que acción quieres realizar: (RESERVAR, ANULAR, CONSULTAR o SALIR): ");

            palabra = entrada.nextLine().toUpperCase().trim();
         
            switch (palabra) {

                case "RESERVAR":
                    ReservarCita();
                    break;
                case "ANULAR":
                    AnularCita();
                    break;
                case "CONSULTAR":
                    ConsultarCita();
                    // menuConsultar
                    break;
                case "SALIR":
                    System.out.println("Salir del programa");
                    break;
                default:
                    System.out.println("Opción errónea...\n");
            }
        } while (!palabra.equals("SALIR"));

        System.out.println("Por último, introduce el nombre de fichero de texto en el que quieres guardar tus reservas: ");
        MetodosComunes.guardarArchivo(horario, "Introduce el nombre de fichero de texto en el que quieres guardar tus reservas: ");
    }

    private static void pedirArchivo() {
        String input;
        boolean fin = false;
        while (!fin) {
            System.out.println(">> Introduce el nombre del fichero de reservas a leer: ");
            input = entrada.nextLine().trim();
            if(input.length() > 0) {
                if(!input.endsWith(".txt")) {
                    input += ".txt";
                }
                if(FileUtils.existe(input)) {
                    FileUtils.parsearArchivo(input, horario);  //Preguntar a victor como se llaman a constructores y esas vainas
                    fin = true;
                }else {
                    System.out.println("ERROR: El fichero " + input + " no existe.");
                }
            } else {
                System.out.println("ERROR: Por favor, introduzca un nombre de archivo.");
            }
        }
    }

    private static void ReservarCita() { 
       
        boolean fin = false;
        String input;
        ArrayList<Dia> dias = horario.getDiasLibres();
        int indiceDia = -1;
        int indiceHora = -1;

        String diasLibres = "";
        for(int i=0;i<dias.size();i++) {
            diasLibres += dias.get(i).getStringFechaSinAnio();
            if(i == dias.size() - 2) {
                diasLibres += " y ";
            }else if(i <= dias.size() - 1) {
                diasLibres += ", ";
            }
        }
        
        while (indiceDia == -1) {  //Meter de todo menos fin para que meta algún valor y no se quede en blanco
            System.out.println("Estos son los dias con huecos:");
            System.out.println(diasLibres);
            System.out.println("Indica el dia deseado:");
            input = entrada.nextLine().trim();
            if(input.length() > 0) {   //Si ha metido un valor
                indiceDia = Dia.indice(dias, input);
                if(indiceDia == -1) {
                    System.out.println("ERROR: Introduce un dia que aparezca en la lista.");
                }
            } else {
                System.out.println("ERROR: Introduce un dia.");
            }
        }

        ArrayList<Hora> horas = dias.get(indiceDia).getHorasLibres();

        while (indiceHora == -1) {  //Meter de todo menos fin para que meta algún valor y no se quede en blanco
            System.out.println("Para dicho dia tenemos huecos en los siguientes horarios:");
            for(int i=0;i<horas.size();i++) {
                System.out.print(horas.get(i));
                if(i < horas.size()-1) {
                    System.out.print(",");
                }
            }
            System.out.println();
            System.out.println("Indica el horario deseado:");
            input = entrada.nextLine().trim();
            if(input.length() > 0) {   //Si ha metido un valor
                indiceHora = Hora.indice(horas, Byte.parseByte(input));
                if(indiceHora == -1) {
                    System.out.println("ERROR: Introduce un horario que aparezca en la lista.");
                }
            } else {
                System.out.println("ERROR: Introduce una hora.");
            }
        }

        String nombre = "";
        int telefono = 0;
        String email = "";
        
        while (!fin) {  //Meter de todo menos fin para que meta algún valor y no se quede en blanco
            System.out.print("Indica tu nombre: ");
            input = entrada.nextLine().trim();  //metes el nombre que quieras
            if(input.length() > 0) {    //esto es para que no meta un nombre en blanco
                nombre = input;
                fin = true;  //sale
            } else {
                System.out.println("ERROR: No puede dejar el nombre en blanco.");
            }
        }
        fin = false;
        
        while (!fin) {  //Meter de todo menos fin para que meta algún valor y no se quede en blanco
            System.out.print("Indica tu telefono: ");
            input = entrada.nextLine().trim();  //metes el nombre que quieras
            if(input.length() > 0) {    //esto es para que no meta un nombre en blanco
                try {
                    telefono = Integer.parseInt(input);
                    fin = true;  //sale
                }catch(NumberFormatException e) {
                    System.out.println("ERROR: Introduzca un telefono valido.");
                }
            } else {
                System.out.println("ERROR: No puede dejar el nombre en blanco.");
            }
        }
        fin = false;

        while (!fin) {  //Meter de todo menos fin para que meta algún valor y no se quede en blanco
            System.out.print("Indica tu e-mail: ");
            input = entrada.nextLine().trim();  //metes el nombre que quieras
            if(input.length() > 0) {    //esto es para que no meta un nombre en blanco
                if(input.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
                    email = input;
                    fin = true;
                }else {
                    System.out.println("ERROR: Introduzca una dirección de e-mail válida.");
                }
            } else {
                System.out.println("ERROR: No puede dejar el nombre en blanco.");
            }
        }
        
        horario.entradasDia.get(indiceDia).entradasHora.get(indiceHora).realizarReserva(nombre, telefono, email);
        //System.out.println("Perfecto, has reservado para");
        System.out.println("Reserva realizada con exito.");
    }

    private static void AnularCita() {
        boolean fin = false;
        String input;
        ArrayList<Cita> citas = new ArrayList<Cita>();

        while (!fin) {  //Meter de todo menos fin para que meta algún valor y no se quede en blanco
            System.out.print("¿Qué email o teléfono tienes?");
            input = entrada.nextLine().trim();
            if(input.length() > 0) {   //Si ha metido un valor
                try {
                    if(input.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) { // es un email
                        citas = horario.buscarCitas(input);
                    }else { // es un telefono
                        citas = horario.buscarCitas(Integer.parseInt(input));
                    }
                    fin = true;
                }catch(NumberFormatException e) {
                    System.out.println("ERROR: Introduce un telefono o un email valido.");    
                }
            } else {
                System.out.println("ERROR: Introduce un telefono o un email valido.");
            }
        }

        if(citas.size() == 0) {
            System.out.println("No tienes reservas.");
        }else if(citas.size() == 1) {
            //-------
        }else {
            System.out.println("Se han encontrado varias reservas. Seleccione una.");
            ArrayList<Date> dt = new ArrayList<Date>();
            
            for(int i=0;i<citas.size();i++){
                if(!dt.contains(citas.get(i).getDia())){  //si no contiene el día
                    dt.add(citas.get(i).getDia());  //Se lo añades
                    System.out.println(Dia.formateaFechaSinAnio(citas.get(i).getDia())); // imprime
                }
            }
            
            System.out.println("Introduzca el dia deseado:");
            
        }
    }
    

    private static void ConsultarCita() {
        boolean fin = false;
        String input;
        ArrayList<Cita> citas = new ArrayList<Cita>();

        while (!fin) {  //Meter de todo menos fin para que meta algún valor y no se quede en blanco
            System.out.print("¿Qué email o teléfono tienes?");
            input = entrada.nextLine().trim();
            if(input.length() > 0) {   //Si ha metido un valor
                try {
                    if(input.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) { // es un email
                        citas = horario.buscarCitas(input);
                    }else { // es un telefono
                        citas = horario.buscarCitas(Integer.parseInt(input));
                    }
                    fin = true;
                }catch(NumberFormatException e) {
                    System.out.println("ERROR: Introduce un telefono o un email valido.");    
                }
            } else {
                System.out.println("ERROR: Introduce un telefono o un email valido.");
            }
        }

        if (citas.size() == 0) {
            System.out.println("No tienes ninguna reserva.");
        }else {
            for(Cita cita : citas) {
                System.out.println(cita.toString());
            }
        }
    }
    
}
