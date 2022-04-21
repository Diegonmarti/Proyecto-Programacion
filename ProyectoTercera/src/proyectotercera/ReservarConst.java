package proyectotercera;

public class ReservarConst {

    private String nombre;
    private String Apellido;
    private String telefono;
    private String mail;

    public ReservarConst(String nombre, String Apellido, String telefono,  String mail) {
        this.nombre = nombre;
        this.Apellido = Apellido;
        this.telefono = telefono;
        this.mail = mail;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String Apellido) {
        this.Apellido = Apellido;
    }
    
    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    public String getmail() {
        return mail;
    }

    public void setmail(String mail) {
        this.mail = mail;
    }
}
