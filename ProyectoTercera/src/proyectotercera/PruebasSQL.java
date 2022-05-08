package proyectotercera;

import java.sql.SQLException;
import java.util.Scanner;

import proyectotercera.utils.DBResult;
import proyectotercera.utils.DBUtils;

public class PruebasSQL {
    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);
        DBUtils.initParams("localhost", "datos", "root", "");
        
        if(DBUtils.connect(true)) {
            DBUtils.executeQuery("CREATE TABLE IF NOT EXISTS alumnos (cod INT PRIMARY KEY AUTO_INCREMENT, nom VARCHAR(20), edad INT CHECK (edad >= 16))");
            String s = "";
            boolean fin = false;
            while(!fin) {
                System.out.println("Quieres INSERTAR, BORRAR, CONSULTAR o SALIR?");
                s = sc.nextLine().toLowerCase();
                switch(s) {
                    case "insertar":
                        System.out.println("Introduzca nombre del alumno: ");
                        String nom = sc.nextLine();
                        System.out.println("Introduzca edad del alumno: ");
                        int edad = Integer.parseInt(sc.nextLine());
                        DBResult r = DBUtils.executeQuery("INSERT INTO alumnos(nom, edad) VALUES (?, ?)", nom, edad);
                        break;
                    case "borrar":
                        System.out.println("Introduzca nombre del alumno a borrar: ");
                        DBUtils.executeQuery("DELETE FROM alumnos WHERE nom = ?", sc.nextLine());
                        break;
                    case "consultar":
                        DBResult res = DBUtils.executeQuery("SELECT * FROM alumnos");

                        System.out.println(res.getColumnName(1) + " - " + res.getColumnName(2) + " - " + res.getColumnName(3));
                        while(res.next()) {
                            System.out.println(res.get(1) + " - " + res.get(2) + " - " + res.get("edad"));
                        }
                        break;
                    case "salir":
                        fin = true;
                        break;
                    default:
                        System.out.println("ERROR: Entrada erronea.");
                        break;
                }
            }
        }
    }
}
