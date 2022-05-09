package proyectotercera;

import java.util.ArrayList;

import proyectotercera.utils.ISerializable;

public class Hora implements ISerializable, Comparable<Hora> {

    private byte horaInicio;
    private byte numAlumnos = 0;
    private String nomAlumno1 = "";//tanto nombre como apellidos
    private int telAlumno1 = 0;
    private String emailAlumno1 = "";

    public Hora(byte horaInicio) {
        this.horaInicio = horaInicio;
    }
   
    // Constructor vacío para poder rellenar desde fromSerializedData()
    public Hora() {}

    public byte realizarReserva(String nomAlumno, int telAlumno, String emailAlumno) {
        if (this.checkRango()) {//compruebo el rango de horaInicio (entre 8 y 20)
            //y modifico numAlumnos a 1 si está en el rango establecido. También guardamos los datos. 
            numAlumnos = 1;
            this.nomAlumno1 = nomAlumno;
            this.telAlumno1 = telAlumno;
            this.emailAlumno1 = emailAlumno;
            return 1;//si todo está OK devolvemos 1 que es lo que nos pedía el enunciado
        }
        return -1;//si está fuera del rango devolvemos -1 que es lo que nos pedían.
    }

    public void borrarReserva() {
        if (this.checkRango()) {//compruebo si está en el rango, si lo está
            //numAlumnos lo pongo a 0
            numAlumnos = 0;
            this.nomAlumno1 = "";  
            this.telAlumno1 = 0;
            this.emailAlumno1 = "";
        }
    }

    public boolean checkRango() {
        return Hora.checkRango(this.horaInicio);
    }

    public static boolean checkRango(byte horaInicio) {
        return horaInicio >= 8 && horaInicio <= 20;
    }
    
    public byte getHoraInicio() {
        return horaInicio;
    }

    public String getHorario() {
        return horaInicio + ":00h - " + (horaInicio + 1) + ":00h";
    }

    public byte getnumAlumnos() {
        return numAlumnos;
    }
    
    public int getTelefono() {
        return telAlumno1;
    }

    public String getEmail() {
        return emailAlumno1;
    }

    public static int indice(ArrayList<Hora> lista, byte hora) {
        for(int i=0;i<lista.size();i++) {
            if(lista.get(i).horaInicio == hora) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String toString() {
        return getHorario();
    }

    @Override
    public String toSerializedData() {
        String out = "%H$";
        // Si la hora es un solo digito, se incluye un 0
        if(this.horaInicio < 10) {
            out += "0";
        }
        out += this.horaInicio + "\n";
        
        // Si hay algun alumno que haya reservado, incluirlo.
        if(this.numAlumnos > 0) {
            out += "%C$" + this.nomAlumno1 + "$" + this.telAlumno1 + "$" + this.emailAlumno1 + "\n";
        }
        return out;
    }

    @Override
    public int fromSerializedData(String[] data) {
        // Para saber si ya hemos rellenado la horaInicial.
        // Se podría hacer con horaInicial == 0, pero eso puede dar problemas
        // si hay un error en los datos recibidos.
        boolean rellenaHora = false;
        
        // Este bucle está por si hay datos que no coincidan antes de leer
        // la hora. Asi se pueden ignorar fácilmente.
        // Ejemplo:
        // %C$nom$000111222$email
        // %H$09
        // %C$nom2$999888777$email2
        int i;
        for(i=0;i<data.length;i++) {
            if(data[i].startsWith("%H$") && !rellenaHora) {
                this.horaInicio = Byte.parseByte(data[i].substring(3));
                rellenaHora = true;
            }else if(data[i].startsWith("%C$") && rellenaHora && this.numAlumnos == 0) {
                String[] datos = data[i].substring(3).split("\\$");
                this.nomAlumno1 = datos[0];
                this.telAlumno1 = Integer.parseInt(datos[1]);
                this.emailAlumno1 = datos[2];
                this.numAlumnos++;
            }else if(rellenaHora) {
                break;
            }
        }
        
        return i;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) {
            return false;
        }

        if(!(obj instanceof Hora)) {
            return false;
        }

        final Hora otro = (Hora)obj;

        return this.horaInicio == otro.horaInicio;
    }

    @Override
    public int compareTo(Hora otro) {
        return Byte.compare(this.horaInicio, otro.horaInicio);
    }
}
