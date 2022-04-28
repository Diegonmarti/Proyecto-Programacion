package proyectotercera;

import java.util.ArrayList;
import java.util.Scanner;

import proyectotercera.utils.FileUtils;


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
            System.out.println("1. RESERVAR");
            System.out.println("2. ANULAR");
            System.out.println("3. CONSULTAR");
            System.out.println("4. SALIR");
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
                    FileUtils.parsearArchivo(input, horario);
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
       
        System.out.print("\nIndique Citas: ");
        int citas = Integer.parseInt(entrada.nextLine());
        System.out.print("Hora: ");
        String hora = entrada.nextLine();
        System.out.println("\n\tDatos de la reserva");
        System.out.print("Nombre: ");
        String nombre = entrada.nextLine();
        System.out.print("Di tu mail: ");
        String mail = entrada.nextLine();
        System.out.print("Teléfono: ");
        String telefono = entrada.nextLine();
            
        // cita[citas].hacerReserva(new ReservarConst(nombre, hora, mail, telefono));

    }

    private static void AnularCita() {
        System.out.print("\nIndique cita para anular: ");
        int citas = Integer.parseInt(entrada.nextLine());
        System.out.print("Hora que quiera anular: ");
        String hora = entrada.nextLine();
        System.out.print("Nombre del archivo para anular: ");
        String nombre = entrada.nextLine();

        // cita[citas].anularReserva(nombre, hora);
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

        for(Cita c : citas) {
            System.out.println(c.getDia() + " a la hora " + c.getHora());
        }
    }
    
}
