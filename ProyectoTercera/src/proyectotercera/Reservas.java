package proyectotercera;

import java.util.ArrayList;

public class Reservas {
    public String etiquetaLugar;
    public ArrayList <Dia> entradasDia=new ArrayList (); //Llama a día
    
    public void leerReserva (){
        for (int i = 0; i < entradasDia.size(); i++) { //Es el length del arraylist
            System.out.println(entradasDia.get(i));
        }
    }
    
    public void addContenido (Dia dia){
        entradasDia.add(dia);
    }
}
