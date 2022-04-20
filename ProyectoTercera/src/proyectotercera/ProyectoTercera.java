package proyectotercera;

import java.util.Scanner;

public class ProyectoTercera {

    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        System.out.println(">Programa de reservas de Tutorías>");
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        System.out.println("Introduce el nombre del fichero de reservas a leer");
        //Hacer el Scanner que llame al fichero

        System.out.println("1. RESERVAR");
        System.out.println("2. ANULAR");
        System.out.println("3. SALIR");
        String palabra = entrada.nextLine();

        switch (palabra.toUpperCase()) {

            case "RESERVAR":
                // menuReservar;
                break;
            case "ANULAR":
                // menuAnular;
                break;
            case "CONSULTAR":
                // menuConsultar
            case "SALIR":
                System.out.println("Salir del programa");

        }
    }
}
