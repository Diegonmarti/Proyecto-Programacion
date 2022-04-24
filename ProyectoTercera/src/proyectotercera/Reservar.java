package proyectotercera;

import java.util.Scanner;
 
public class Reservar {

    //private static Reservar[] cita;
    private static Scanner entrada;

    public static void main(String[] args) {
        entrada = new Scanner(System.in);
        /*cita = new Reservar[10];
        for (int i = 0; i < cita.length; i++) {
            cita[i] = new Reservar();
        }*/
        
        String palabra;
        do {
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            System.out.println(">Programa de reservas de Tutorías>");
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            System.out.println("Introduce el nombre del fichero de reservas a leer");
            //Hacer el Scanner que llame al fichero

            System.out.println("1. RESERVAR");
            System.out.println("2. ANULAR");
            System.out.println("3. CONSULTAR");
            System.out.println("4. SALIR");
            palabra = entrada.nextLine().toUpperCase();
         
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
        System.out.print("Hora a reservar: ");
        String hora = entrada.nextLine();
        boolean hayDisponibles = false;

        System.out.println("\nCitas disponibles: ");
        /*for (int i = 0; i < cita.length; i++) {
            if (cita[i].estaDisponible(hora)) {
                System.out.println("- Citas #" + i);
                hayDisponibles = true;
            }
        }*/

        if (!hayDisponibles) {
            System.out.println("\nNo hay citas disponibles para esa hora");
        }
    }
}
