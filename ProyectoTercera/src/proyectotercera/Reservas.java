package proyectotercera;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import proyectotercera.utils.ISerializable;

public class Reservas implements ISerializable {
    public String etiquetaLugar;
    public String nombreTutor;
    public ArrayList<Dia> entradasDia = new ArrayList<Dia>();

    public Reservas(String nombreTutor) {
        this.nombreTutor = nombreTutor;
    }
    
    public Reservas() {}
    
    public void leerReserva (){
        for (int i = 0; i < entradasDia.size(); i++) { //Es el length del arraylist
            entradasDia.get(i).leerDia();
        }
    }

    // Esto se puede mejorar rellenando el ArrayList<Cita> previamente y solo iterar sobre este
    public ArrayList<Cita> buscarCitas(int telefono) {
        ArrayList<Cita> out = new ArrayList<Cita>();
        for(Dia dia : entradasDia) {
            for(Hora hora : dia.entradasHora) {
                if(hora.getTelefono() == telefono) {
                    out.add(new Cita(dia.fecha, hora));
                }
            }
        }
        return out;
    }

    public ArrayList<Cita> buscarCitas(String email) {
        ArrayList<Cita> out = new ArrayList<Cita>();
        for(Dia dia : entradasDia) {
            for(Hora hora : dia.entradasHora) {
                if(hora.getEmail().equals(email)) {
                    out.add(new Cita(dia.fecha, hora));
                }
            }
        }
        return out;
    }

    public ArrayList<Dia> getDiasLibres() {
        ArrayList<Dia> out = new ArrayList<Dia>();
        for(Dia dia : entradasDia) {
            for(Hora hora : dia.entradasHora) {
                if(hora.getnumAlumnos() == 0) {
                    out.add(dia);
                    break;
                }
            }
        }
        
        return out;
    }
    
    public void addContenido(Dia dia) {
        entradasDia.add(dia);
    }

    public boolean contiene(Dia dia) {
        return entradasDia.contains(dia);
    }

    public void rellenarHoras(ArrayList<Hora> horas) {
        for(Dia dia : entradasDia) {
            dia.rellenaHoras(horas);
        }
    }

    public void rellenarHoras(Hora hora) {
        for(Dia dia : entradasDia) {
            dia.addContenido(hora);
        }
    }

    public int numDias() {
        return entradasDia.size();
    }

    @Override
    public String toSerializedData() {
        String out = "%N$" + this.nombreTutor + "\n";
        
        Collections.sort(entradasDia);

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
