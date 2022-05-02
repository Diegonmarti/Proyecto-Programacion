package proyectotercera;

import java.util.ArrayList;
import java.util.Scanner;

import proyectotercera.utils.MetodosComunes;

public class CrearHorario {

    private static Scanner entrada;
    static Reservas horario;
    public static void main(String[] args) {
        entrada = new Scanner(System.in);
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        System.out.println("> Construcción de horarios de tutorias >");
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        System.out.println();
        pedirNombre();
        // Entrada dias
        System.out.println("Escribe en lineas diferentes las fechas de los dias de tu horario. Cuando no quieras introducir más dias, escribe FIN: ");   
        pedirDias();
        // Entrada horas
        System.out.println("Perfecto, indica ahora las horas de inicio de cada sesión de reserva. Cuando no quieras introducir más horas, escribe FIN: ");
        pedirHora();
        // Entrada nombre archivo
        System.out.println("Por último, introduce el nombre de fichero de texto en el que quieres guardar tu horario: ");
        MetodosComunes.guardarArchivo(horario, "Introduce el nombre de fichero de texto en el que quieres guardar tu horario: ");
        // FIN
        System.out.println("Fichero creado. Muchas gracias :)");
    }

    public static void pedirNombre() {
        String input;
        boolean fin = false;
        while (!fin) {  //Meter de todo menos fin para que meta algún valor y no se quede en blanco
            System.out.print("Nombre del profesor: ");
            input = entrada.nextLine().trim();  //metes el nombre que quieras
            if(input.length() > 0) {    //esto es para que no meta un nombre en blanco
                horario = new Reservas(input); //si la entrada es correcta te lo mete en horario
                fin = true;  //sale
            } else {
                System.out.println("ERROR: No puede dejar el nombre en blanco.");
            }
        }
    }

    public static void pedirDias() {
        String input;
        boolean fin = false;
        Dia d;
        while (!fin) {  //Meter de todo menos fin para que meta algún valor y no se quede en blanco
            input = entrada.nextLine().trim(); //Metes un día
            if(input.length() > 0) {   //Si ha metido un valor
                if(input.equalsIgnoreCase("FIN")) { 
                    if(horario.numDias() > 0) {  //si el horario tiene entradas puedes hacer fin 
                        fin = true;
                    } else {
                        System.out.println("ERROR: Introduce por lo menos un dia.");
                    }
                }else {
                    if(Dia.checkFecha(input)) {
                        d = new Dia(input);
                        if(!horario.contiene(d)) {
                            horario.addContenido(d);
                        }else {
                            System.out.println("ERROR: Fecha ya existente.");
                        }
                    }else {
                        System.out.println("ERROR: Fecha no válida.");
                        System.out.println("Asegúrese de que la fecha tiene el formato DD/MM/AA y que sea una fecha válida.");
                    }
                }
            } else {
                System.out.println("ERROR: No puede dejar la fecha en blanco.");
            }
        }
        
    }

    public static void pedirHora() {
        String input;
        byte horaInicial;
        boolean fin = false;
        ArrayList<Hora> horas = new ArrayList<Hora>();
        Hora h;

        while(!fin) {
            input = entrada.nextLine().trim();
            if(input.equalsIgnoreCase("FIN")) {
                if(horas.size() > 0) {
                    fin = true;
                } else {
                    System.out.println("ERROR: Introduce por lo menos una hora.");
                }
            }else {
                if(input.matches("\\d+")) { // Usamos la expresión regular "[0-9]+" para la comprobación del if
                    try {
                        horaInicial = Byte.parseByte(input); // Lo parseamos a byte
                        if(Hora.checkRango(horaInicial)) {
                            h = new Hora(horaInicial);

                            if(!horas.contains(h)){
                                horas.add(h); // lo rellenamos
                            }else {
                                System.out.println("ERROR: Hora ya existente.");
                            }
                        } else {
                            System.out.println("ERROR: Introduzca una hora entre las 8 y las 20.");
                        }
                    }catch(NumberFormatException e) {
                        System.out.println("ERROR: Introduzca una hora entre las 8 y las 20.");
                    }
                } else {
                    System.out.println("ERROR: Introduzca un número o FIN para terminar.");
                }
            }
        }

        horario.rellenarHoras(horas);
    }
}
