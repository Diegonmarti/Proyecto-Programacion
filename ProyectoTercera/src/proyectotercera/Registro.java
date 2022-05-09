package proyectotercera;

import java.util.Scanner;

import proyectotercera.utils.DBResult;
import proyectotercera.utils.DBUtils;
import proyectotercera.utils.MetodosComunes;

public class Registro {

    public static void main(String[] args) {

        if(MetodosComunes.conectarDB()) {
            System.out.println("Bienvenido al programa de Registro de alumnos.");
            System.out.println("Por favor, siga las instrucciones a la hora de introducir los datos que se le piden.");
            System.out.println();

            String nombre;
            int telefono;
            String email;
            String password;
            
            boolean registrando = true;
            while(registrando) {
                nombre = MetodosComunes.pedirNombre("Introduzca el nombre del alumno: ");
                telefono = MetodosComunes.pedirTelefono("Introduzca el telefono del alumno: ");
                email = MetodosComunes.pedirEmail("Introduzca el e-mail del alumno: ");
                password = MetodosComunes.pedirPassword("Introduzca una contraseña: ", "Introduzca la contraseña otra vez para confirmar: ", true);

                // Usamos la funcion MD5 de MySQL para encriptar las contraseñas
                DBResult res = DBUtils.executeQuery("INSERT INTO alumnos(nombre, tlf, email, passwd) VALUES (?, ?, ?, MD5(?))", nombre, telefono, email, password);
                if(!res.isError() && res.getUpdateCount() == 1) {
                    System.out.println("Alumno registrado correctamente.");
                }else {
                    System.out.println("Lo sentimos, ha habido un error inesperado al registrar al alumno.");
                }
                registrando = MetodosComunes.pedirConfirmacion("¿Quiere seguir registrando alumnos? (SI/NO)");
            }

            System.out.println("Fin del registro de alumnos.");
        }else {
            System.out.println("Error al conectarse a la base de datos. Revise el archivo de configuracion y vuelva a intentarlo.");
        }
    }
}
