package proyectotercera;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import proyectotercera.utils.ISerializable;

public class Dia implements ISerializable, Comparable<Dia> {

    public String etiquetaDia;
    public Date fecha;
    public ArrayList<Hora> entradasHora = new ArrayList<Hora>();
    public static DateFormat formatoFecha = new SimpleDateFormat("dd/MM/yy");

    public Dia(String entradaDia) {
        this.setFecha(entradaDia);
    }

    public Dia() {}

    public void leerDia() {
        System.out.println(formatoFecha.format(this.fecha));
        for (int i = 0; i < entradasHora.size(); i++) {
            System.out.println(entradasHora.get(i));
        }
    }

    public void setFecha(String entradaDia) {
        try {
            this.fecha = formatoFecha.parse(entradaDia);
        }catch(ParseException e) {
            // Si da error, pon la fecha al 01/01/1970 (epoch) fecha default
            this.fecha = Date.from(Instant.ofEpochMilli(0));
        }
    }

    public static boolean checkFecha(String fecha) {
        try {
            formatoFecha.setLenient(false);
            formatoFecha.parse(fecha);
            return true;
        }catch(ParseException e) {
            return false;
        }
    }

    public void addContenido(Hora hora) {
        entradasHora.add(hora);
    }

    public void rellenaHoras(ArrayList<Hora> horas) {
        entradasHora.addAll(horas);
    }

    public String getStringFecha() {
        return formatoFecha.format(this.fecha);
    }

    @Override
    public String toSerializedData() {
        String out = "%D$" + formatoFecha.format(this.fecha) + "\n";
        
        Collections.sort(entradasHora); 

        for(Hora hora : entradasHora) {
            out += hora.toSerializedData();
        }
        return out;
    }
    
    @Override
    public int fromSerializedData(String[] data) {        
        Hora hora;
        this.setFecha(data[0].substring(3));
        
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

    @Override
    public boolean equals(Object obj) {
        if(obj == null) {
            return false;
        }

        if(!(obj instanceof Dia)) {
            return false;
        }

        final Dia otro = (Dia)obj;

        return this.fecha.equals(otro.fecha);
    }

    @Override
    public int compareTo(Dia otro) {
        if(otro==null){
           throw new NullPointerException();
        }

        return fecha.compareTo(otro.fecha);
    }
}
