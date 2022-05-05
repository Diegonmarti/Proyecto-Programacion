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
     * @param data El String que contiene los datos, separado por los `\n`.
     * @return La cantidad de lineas que se han utilizado.
     */
    public int fromSerializedData(String[] data);
    
    /**
     * Wrapper del método fromSerializedData para una entrada String.
     * 
     * @param data El String que contiene los datos.
     * @return La cantidad de lineas que se han utilizado.
     */
    public default int fromSerializedData(String data) {
        return this.fromSerializedData(data.split("\n")); //separa en saltos de lineas
    }
}
