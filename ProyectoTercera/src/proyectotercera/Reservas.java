package proyectotercera;

import java.util.ArrayList;

public class Reservas {
    public String etiquetaLugar;
    public ArrayList <Dia> entradasDia=new ArrayList ();
    
    public void leerReserva (){
        for (int i = 0; i < entradasDia.size(); i++) {
            System.out.println(entradasDia.get(i));
        }
    }
    
    public void addContenido (Dia dia){
        entradasDia.add(dia);
    }
}
