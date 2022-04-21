package proyectotercera;

import java.util.Scanner;

public class Hora {

    private byte horaInicio;
    private byte numAlumnos=0;
    private String nomAlumno1="";//tanto nombre como apellidos
    private int telAlumno1=0;
    private String emailAlumno1="";
    static Scanner sc = new Scanner(System.in);

   public Hora(byte horaInicio) {
        this.horaInicio = horaInicio;
    }  

    public byte realizarReserva(String nomAlumno, int telAlumno, String emailAlumno) {
        if (horaInicio >= 8 && horaInicio <= 20) {//compruebo el rango de horaInicio (entre 8 y 20)
            //y modifico numAlumnos a 1 si está en el rango establecido. También guardamos los datos. 
            numAlumnos = 1;
            this.nomAlumno1 = nomAlumno1;
            this.telAlumno1 = telAlumno1;
            this.emailAlumno1 = emailAlumno1;
            return 1;//si todo está OK devolvemos 1 que es lo que nos pedía el enunciado
        }
        return -1;//si está fuera del rango devolvemos -1 que es lo que nos pedían.
    }

    public void borrarReserva() {
        if (horaInicio >= 8 && horaInicio <= 20) {//compruebo si está en el rango, si lo está
            //numAlumnos lo pongo a 0
            numAlumnos = 0;
//            this.nomAlumno1 = ""; HABLAR CON EL JEFE VICTOR 
//            this.telAlumno1 = 0;
//            this.emailAlumno1 = "";
        }
    }

    public String getHorario() {
        return horaInicio + ":00h - " + (horaInicio + 1) + ":00h";
    }

    public byte getnumAlumnos() {
        return numAlumnos;
    }

    @Override
    public String toString() {
        return nomAlumno1 + ", " + telAlumno1 + ", " + emailAlumno1 + " // " + getHorario();
    }
}
