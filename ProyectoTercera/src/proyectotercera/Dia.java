package proyectotercera;

import java.util.ArrayList;
import java.util.Scanner;

public class Dia {

    public String etiquetaDia;
    public ArrayList<Hora> entradasHora = new ArrayList();  //Llama a hora
    static Scanner sc = new Scanner(System.in);

    public Dia(ArrayList<Hora> entradasHora) {
        this.entradasHora = entradasHora;
    }

    public void leerDia() {
        for (int i = 0; i < entradasHora.size(); i++) {
            System.out.println(entradasHora.get(i));
        }
    }

    public void addContenido(Hora hora) {
        entradasHora.add(hora);
    }
}
