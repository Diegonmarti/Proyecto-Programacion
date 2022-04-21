package proyectotercera;

import proyectotercera.ReservarConst;

public class Reservar {

    private ReservarConst[] reservas;

    public Reservar() {
        reservas = new ReservarConst[3];
    }
    
    //private int getPosArray(String hora) {
    //    for(int i=0;i<reservas.length;i++){
    //        if(hora.equals(i){
    //            return i;
    //        }else{
    //            return -1; //En realidad tendría que saltar una exception o poner un mensaje de error
    //        }
    //    }
   // }
    
    private int getPosArray(String hora) {
        if (hora.equals("9:00")) {
            return 0;
        } else if (hora.equals("10:00")) {
            return 1;
        } else {
            return 2;
        }
    }

    public void hacerReserva(ReservarConst reserva) {
        int pos = getPosArray(reserva.getHora());
        if (reservas[pos] != null) {
            System.out.println("Esta HORA ya está reservada");
        } else {
            reservas[pos] = reserva;
            System.out.println("RESERVA efectuada");
        }
    }

    public void anularReserva(String nombre, String hora) {
        int pos = getPosArray(hora);
        if (reservas[pos] != null) {
            if (reservas[pos].getNombre().equals(nombre)) {
                reservas[pos] = null;
                System.out.println("RESERVA anulada");
            } else {
                System.out.println("EL NOMBRE no se corresponde con la persona que hizo la RESERVA");
            }
        } else {
            System.out.println("No hay ninguna RESERVA para la HORA indicada");
        }
    }

    public boolean estaDisponible(String hora) {
        return reservas[getPosArray(hora)] == null;
    }

}
