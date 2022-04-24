package proyectotercera.utils;

public interface ISerializable {

    /**
     * Método utilizado para generar una representación en String de los datos
     * de un objeto.
     * 
     * @return Representacion de los datos en forma de texto.
     */
    public String toSerializedData();

    /**
     * Método utilizado para reconstruir un objeto a partir de un String
     * representando sus datos.
     * 
     * @param data El String que contiene los datos.
     */
    public void fromSerializedData(String data);
}
