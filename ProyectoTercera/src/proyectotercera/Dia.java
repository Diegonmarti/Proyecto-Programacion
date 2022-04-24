package proyectotercera;

import java.util.ArrayList;
import java.util.Arrays;
import proyectotercera.utils.ISerializable;

public class Dia implements ISerializable {

    public String etiquetaDia;
    public ArrayList<Hora> entradasHora = new ArrayList();  //Llama a hora

    public Dia(ArrayList<Hora> entradasHora) {
        this.entradasHora = entradasHora;
    }
    public Dia() {}

    public void leerDia() {
        System.out.println(etiquetaDia);
        for (int i = 0; i < entradasHora.size(); i++) {
            System.out.println(entradasHora.get(i));
        }
    }

    public void addContenido(Hora hora) {
        entradasHora.add(hora);
    }

    @Override
    public String toSerializedData() {
        String out = "%D$" + this.etiquetaDia + "\n";
        
        for(Hora hora : entradasHora) {
            out += hora.toSerializedData();
        }
        return out;
    }
    
    @Override
    public int fromSerializedData(String[] data) {        
        Hora hora;
        this.etiquetaDia = data[0].substring(3);
        
        int i=1;
        String[] modData;
        while(i < data.length) {
            if(data[i].startsWith("%H$")) {
                modData = Arrays.copyOfRange(data, i, data.length);
                hora = new Hora();
                i += hora.fromSerializedData(modData);
                entradasHora.add(hora);
            }else {
                break;
            }
        }
        
        return i;
    }
}
