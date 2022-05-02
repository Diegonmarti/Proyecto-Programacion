package proyectotercera;

import java.util.Date;

public class Cita {
    private Date dia;
    private Hora hora;

    public Cita(Date dia, Hora hora) {
        this.dia = dia;
        this.hora = hora;
    }

    public Date getDia() {
        return dia;
    }

    public Hora getHora() {
        return hora;
    }

    @Override
    public String toString() {
        return Dia.formateaFechaSinAnio(dia) + " a las " + hora.toString();
    }

    // @Override
    // public boolean equals(Object obj) {
    //     if(obj == null) {
    //         return false;
    //     }

    //     if(!(obj instanceof Cita)) {
    //         return false;
    //     }

    //     final Cita otro = (Cita)obj;

    //     if(this.dia.equals(otro.dia) && this.hora == this.)
    // }
}
