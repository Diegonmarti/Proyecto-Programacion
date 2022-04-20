package proyectotercera.utils.tests;

import proyectotercera.utils.ISerializable;

public class ClaseA implements ISerializable {
    private byte horaInicio;
    private String nombre;
    private String mail;

    public ClaseA(byte horaInicio, String nombre, String mail) {
        this.horaInicio = horaInicio;
        this.nombre = nombre;
        this.mail = mail;
    }
    
    public ClaseA() {}

    @Override
    public String toString() {
        return "ClaseA{" + "horaInicio=" + horaInicio + ", nombre=" + nombre + ", mail=" + mail + '}';
    }
    
    @Override
    public String toSerializedData() {
        return "%H$" + (horaInicio < 10 ? "0" + horaInicio : horaInicio) + "\n" +
               "%C$" + nombre + "$" + mail;
    }

    @Override
    public void fromSerializedData(String data) {
        String[] lines = data.split("\n");
        horaInicio = Byte.parseByte(lines[0].substring(3));
        nombre = lines[1].substring(3).split("\\$")[0];
        mail = lines[1].substring(3).split("\\$")[1];
    }
    
}
