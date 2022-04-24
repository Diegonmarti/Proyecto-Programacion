package proyectotercera;

import proyectotercera.utils.FileUtils;

public class TestPruebasxLeer {
    public static void main(String[] args) {
        Reservas r = new Reservas();
        FileUtils.parsearArchivo("HorarioPruebas.txt", r);
        System.out.println("");
        System.out.println("");
        System.out.println("=======================");
        System.out.println("");
        System.out.println("");
        r.leerReserva();
    }
}
