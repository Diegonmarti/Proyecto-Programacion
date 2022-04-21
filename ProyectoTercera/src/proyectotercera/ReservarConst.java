package proyectotercera;

public class ReservarConst {

    private String nombre;
    private String hora;
    private String mail;
    private String telefono;

    public ReservarConst(String nombre, String hora, String mail, String telefono) {
        this.nombre = nombre;
        this.hora = hora;
        this.mail = mail;
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getmail() {
        return mail;
    }

    public void setmail(String mail) {
        this.mail = mail;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

}
