package proyectotercera;

import proyectotercera.utils.FileUtils;

public class TestPruebasx {
    public static void main(String[] args) {
        Reservas r = new Reservas();
        r.nombreTutor = "Juan Perez";
        Dia d = new Dia();
        d.etiquetaDia = "09/03/2022";
        d.addContenido(new Hora((byte)9));
        d.addContenido(new Hora((byte)11));
        d.addContenido(new Hora((byte)12));
        
        r.addContenido(d);
        
        d = new Dia();
        d.etiquetaDia = "12/03/2022";
        d.addContenido(new Hora((byte)8));
        Hora h = new Hora((byte)15);
        h.realizarReserva("Pepe", 400111222, "pepe43@nomail.com");
        d.addContenido(h);
        
        h = new Hora((byte)20);
        h.realizarReserva("Roberto", 456000654, "elrober@nomail.com");
        d.addContenido(h);
        
        r.addContenido(d);
        
        d = new Dia();
        d.etiquetaDia = "17/04/2022";
        d.addContenido(new Hora((byte)9));
        h = new Hora((byte)10);
        h.realizarReserva("Roberto", 456000654, "elrober@nomail.com");
        d.addContenido(h);
        
        h = new Hora((byte)11);
        h.realizarReserva("Pepe", 400111222, "pepe43@nomail.com");
        d.addContenido(h);
        
        d.addContenido(new Hora((byte)12));
        h = new Hora((byte)13);
        h.realizarReserva("Fran", 303404505, "fransisco@nomail.com");
        d.addContenido(h);
        
        d.addContenido(new Hora((byte)14));
        
        r.addContenido(d);
        r.leerReserva();
        FileUtils.escribirArchivo("HorarioPruebas.txt", r);
    }
}
