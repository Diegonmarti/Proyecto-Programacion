package proyectotercera;

import java.util.ArrayList;
import java.util.Arrays;
import proyectotercera.utils.ISerializable;

public class Reservas implements ISerializable {
    public String etiquetaLugar;
    public String nombreTutor;
    public ArrayList <Dia> entradasDia=new ArrayList (); //Llama a día

    public Reservas(String nombreTutor) {
        this.nombreTutor = nombreTutor;
    }
    
    public Reservas() {}
    
    public void leerReserva (){
        for (int i = 0; i < entradasDia.size(); i++) { //Es el length del arraylist
            entradasDia.get(i).leerDia();
        }
    }
    
    public void addContenido (Dia dia){
        entradasDia.add(dia);
    }

    public void rellenarHoras (ArrayList<Hora> horas) {
        for(Dia dia : entradasDia) {
            for(Hora hora : horas) {
                dia.addContenido(hora);
            }
        }
    }

    public void rellenarHoras (Hora hora) {
        for(Dia dia : entradasDia) {
            dia.addContenido(hora);
        }
    }

    @Override
    public String toSerializedData() {
        String out = "%N$" + this.nombreTutor + "\n";
        
        for(Dia dia : entradasDia) {
            out += dia.toSerializedData();
        }
        return out;
    }

    @Override
    public int fromSerializedData(String[] data) {        
        Dia dia;
        this.nombreTutor = data[0].substring(3);
        
        int i=1;
        String[] modData;
        while(i < data.length) {
            if(data[i].startsWith("%D$")) {
                modData = Arrays.copyOfRange(data, i, data.length);
                dia = new Dia();
                i += dia.fromSerializedData(modData);
                entradasDia.add(dia);
            }else {
                break;
            }
        }
        
        return i;
    }
}
