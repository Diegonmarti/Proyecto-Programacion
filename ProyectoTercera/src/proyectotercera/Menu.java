package proyectotercera;

import java.util.Scanner;
 
public class Menu {

    private static Scanner entrada;

    public static void main(String[] args) {
       // Menu();
        entrada = new Scanner(System.in);

        Scanner entrada = new Scanner(System.in);
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
            palabra = entrada.nextLine();
         
            switch (palabra.toUpperCase()) {

                case "RESERVAR":
                    // menuReservar;
                    break;
                case "ANULAR":
                    // menuAnular;
                    break;
                case "CONSULTAR":
                    // menuConsultar
                    break;
                case "SALIR":
                    System.out.println("Salir del programa");
                    break;
                default:
                    System.out.println("Opción errónea...\n");
            }
        } while (palabra != "SALIR");
    }
   // public static void Menu (){
   //     entrada = new Scanner(System.in);
//
  //      Scanner entrada = new Scanner(System.in);
    //    String palabra;
    //    do {
    //        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
    //        System.out.println(">Programa de reservas de Tutorías>");
     //       System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
     //       System.out.println("Introduce el nombre del fichero de reservas a leer");
            //Hacer el Scanner que llame al fichero
     //       System.out.println("1. RESERVAR");
     //       System.out.println("2. ANULAR");
     //       System.out.println("3. CONSULTAR");
     //       System.out.println("4. SALIR");
     //       palabra = entrada.nextLine();
 
    //        switch (palabra.toUpperCase()) {

    //            case "RESERVAR":
                    // menuReservar;
    //                break;
    //            case "ANULAR":
                    // menuAnular;
    //                break;
    //            case "CONSULTAR":
                    // menuConsultar
    //                break;
    //            case "SALIR":
    //                System.out.println("Salir del programa");
    //                break;
    //            default:
    //                System.out.println("Opción errónea...\n");
    //        }
    //    } while (palabra != "SALIR");
    // }
}
