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

        if(dias.size() == 0) {
            System.out.println("Lo sentimos, no quedan huecos libres para reservar.");
            return; // es return a secas, porque la función no devuelve nada (es void), si se ejecuta
            // esta línea, se sale de la función.
        }

        Date fechaReserva = null;
        byte horaInicial = 0;

        int indiceDate = -1;
        if(dias.size() == 1) {
            System.out.println("Solo nos queda un dia libre. Se ha seleccionado automaticamente.");
            indiceDate = 0;
        }else {
            String diasLibres = "";
            for(int i=0;i<dias.size();i++) {
                diasLibres += dias.get(i).getStringFechaSinAnio();
                if(i == dias.size() - 2) {
                    diasLibres += " y ";
                }else if(i < dias.size() - 2) {
                    diasLibres += ", ";
                }
            }
            
            while (indiceDate == -1) {  //Meter de todo menos fin para que meta algún valor y no se quede en blanco
                System.out.println("Estos son los dias con huecos:");
                System.out.println(diasLibres);
                System.out.println("Indica el dia deseado:");
                input = entrada.nextLine().trim();
                if(input.length() > 0) {   //Si ha metido un valor
                    indiceDate = Dia.indice(dias, input);
                    if(indiceDate == -1) {
                        System.out.println("ERROR: Introduce un dia que aparezca en la lista.");
                    }
                } else {
                    System.out.println("ERROR: Introduce un dia.");
                }
            }
        }
        fechaReserva = dias.get(indiceDate).fecha;

        ArrayList<Hora> horas = dias.get(indiceDate).getHorasLibres();

        int indiceAux = -1;
        if(horas.size() == 1) {
            System.out.println("Solo nos queda una hora libre para ese dia. Se ha seleccionado automaticamente.");
            indiceAux = 0;
        }else {
            String horasLibres = "";
            for(int i=0;i<horas.size();i++) {
                horasLibres += horas.get(i);
                if(i != horas.size() - 1) {
                    horasLibres += ", ";
                }
            }

            while (indiceAux == -1) {
                System.out.println("Para dicho dia tenemos huecos en los siguientes horarios:");
                System.out.println(horasLibres);
                System.out.println("Indica el horario deseado:");
                input = entrada.nextLine().trim();
                if(input.length() > 0) {   //Si ha metido un valor
                    indiceAux = Hora.indice(horas, Byte.parseByte(input));
                    if(indiceAux == -1) {
                        System.out.println("ERROR: Introduce un horario que aparezca en la lista.");
                    }
                } else {
                    System.out.println("ERROR: Introduce una hora.");
                }
            }
        }
        horaInicial = horas.get(indiceAux).getHoraInicio();

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
                System.out.println("ERROR: No puede dejar el campo en blanco.");
            }
        }
        
        int indiceDia = Dia.indice(horario.entradasDia, fechaReserva);
        int indiceHora = Hora.indice(horario.entradasDia.get(indiceDia).entradasHora, horaInicial);

        horario.entradasDia.get(indiceDia).entradasHora.get(indiceHora).realizarReserva(nombre, telefono, email);
        //System.out.println("Perfecto, has reservado para");
        System.out.println("Reserva realizada con exito.");
    }

    private static void AnularCita() {
        boolean fin = false;
        String input;
        ArrayList<Cita> citas = new ArrayList<Cita>();
        //CONTROLAR QUE LA ENTRADA O ES EMAIL O ES TELÉFONO
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

        fin = false;
        
        if(citas.size() == 0) {
            System.out.println("No tienes reservas.");
        }else {
            Date fechaReserva = null;
            byte horaInicial = 0;
            
            if(citas.size() > 1) { // si hay 2+ citas
                System.out.println("Se han encontrado varias reservas. Seleccione una.");
                ArrayList<Date> dates = new ArrayList<Date>();
                
                // GUARDAMOS LOS DIAS DE LAS RESERVAS SIN DUPLICARLOS
                for(int i=0;i<citas.size();i++){
                    if(!dates.contains(citas.get(i).getDia())){  //si no contiene el día
                        dates.add(citas.get(i).getDia());  //Se lo añades
                    }
                }

                int indiceDate = -1;
                if(dates.size() == 1) {
                    System.out.println("Solo has reservado en un dia. Se ha seleccionado automaticamente.");
                    indiceDate = 0;
                }else {
                    // CONSTRUIR LA SALIDA MEDIANTE COMAS Y LA PENULTIMA POS UN Y
                    String diasReservados = "";
                    for(int i=0;i<dates.size();i++) {
                        diasReservados += Dia.formateaFechaSinAnio(dates.get(i));
                        if(i == dates.size() - 2) { //SI ES LA PENULTIMA POS LE AÑADIMOS "Y"
                            diasReservados += " y ";
                        }else if(i < dates.size() - 2) { //SI NO ","
                            diasReservados += ", ";
                        }
                    }
                    

                    while (indiceDate == -1) {
                        System.out.println("Estos son los dias en los que has reservado:");
                        System.out.println(diasReservados);
                        System.out.println("Indica el dia deseado:");
                        input = entrada.nextLine().trim();
                        if(input.length() > 0) {   //Si ha metido un valor
                            for(int i=0;i<dates.size();i++) {
                                if(Dia.formateaFechaSinAnio(dates.get(i)).equals(input)) { //BUSCAMOS EN EL ARRAYLIST EL CONTENIDO
                                    indiceDate = i;
                                    break;
                                }
                            }
                            if(indiceDate == -1) {
                                System.out.println("ERROR: Introduce un dia que aparezca en la lista.");
                            }
                        } else {
                            System.out.println("ERROR: Introduce un dia.");
                        }
                    }
                }
                fechaReserva = dates.get(indiceDate);

                ArrayList<Hora> horas = new ArrayList<Hora>();
                
                //RECORRES EL ARRAYLIST DE CITAS Y TE GUARDAS SU HORA
                for(int i=0;i<citas.size();i++){
                    if(citas.get(i).getDia().equals(fechaReserva)){
                        horas.add(citas.get(i).getHora());
                    }
                }

                
                int indiceAux = -1;
                if(horas.size() == 1) {
                    System.out.println("Solo has reservado en un hora. Se ha seleccionado automaticamente.");
                    indiceAux = 0;
                }else {
                    String horasReservadas = "";
                    for(int i=0;i<horas.size();i++) {
                        horasReservadas += horas.get(i);
                        if(i != horas.size() - 1) {
                            horasReservadas += ", ";
                        }
                    }
                    
                    while (indiceAux == -1) {
                        System.out.println("Estos son los horarios que reservaste para ese dia:");
                        System.out.println(horasReservadas);
                        System.out.println("Indica el horario deseado:");
                        input = entrada.nextLine().trim();
                        if(input.length() > 0) {   //Si ha metido un valor
                            try {
                                indiceAux = Hora.indice(horas, Byte.parseByte(input)); // IMPORTANTE CONTROLAR
                                if(indiceAux == -1) {
                                    System.out.println("ERROR: Introduce un horario que aparezca en la lista.");
                                }
                            }catch(NumberFormatException e) {
                                indiceAux = -1; // Devolvemos a -1 por si acaso
                                System.out.println("ERROR: Introduce un numero.");
                            }
                        } else {
                            System.out.println("ERROR: Introduce una hora.");
                        }
                    }
                }
                horaInicial = horas.get(indiceAux).getHoraInicio();
            }

            fin = false;
            
            if(citas.size() == 1) {
                fechaReserva = citas.get(0).getDia();
                horaInicial = citas.get(0).getHora().getHoraInicio();
            }

            int indiceDia = Dia.indice(horario.entradasDia, fechaReserva);
            
            int indiceHora = Hora.indice(horario.entradasDia.get(indiceDia).entradasHora, horaInicial);

            Hora horaAnular = horario.entradasDia.get(indiceDia).entradasHora.get(indiceHora);
            String reservaAnular = Dia.formateaFechaSinAnio(fechaReserva) + " a las " + horaAnular;

            boolean anular = false;
            while(!fin) {
                System.out.println("Vas a anular la reserva:");
                System.out.println(reservaAnular);
                System.out.println("¿Quieres anularla? (SI/NO)");
                input = entrada.nextLine().trim();
                if(input.length() > 0) {   //Si ha metido un valor
                    if(input.equalsIgnoreCase("SI")) {
                        anular = true;
                        fin = true;
                    }else if(input.equalsIgnoreCase("NO")) {
                        anular = false;
                        fin = true;
                    }else {
                        System.out.println("ERROR: Introduce SI o NO.");
                    }
                } else {
                    System.out.println("ERROR: Entrada vacia. Introduce SI o NO.");
                }
            }
            if(anular) {
                horaAnular.borrarReserva();
                System.out.println("Perfecto. Reserva anulada.");
            }else {
                System.out.println("Perfecto. Operación ANULAR cancelada.");
            }
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
