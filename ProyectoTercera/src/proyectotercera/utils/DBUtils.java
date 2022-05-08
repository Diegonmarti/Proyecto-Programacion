package proyectotercera.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class DBUtils {
    private static String url = "jdbc:mysql://";
    private static String addr = "";
    private static String dbname = "";
    private static String user = "";
    private static String pass = "";
    
    private static Connection conn;
    
    public static void initParams(String inAddr, String inDBname, String inUser, String inPass) {
        addr = inAddr;
        dbname = inDBname;
        user = inUser;
        pass = inPass;
    }
    
    public static boolean connect(boolean createIfNotExists) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url+addr+"/"+dbname+"?createDatabaseIfNotExist="+createIfNotExists, user, pass);
            return true;
        }catch(SQLException | ClassNotFoundException e) {
            return false;
        }
    }
    
    // Object... es lo mismo que un Object[]. La diferencia es que con este puedes poner los elementos del array simplemente separados por comas
    // TODO: Comentar lo de que setObject nos permite ignorar el tipo de entrada pq lo convierte solo
    public static DBResult executeQuery(String query, Object... params) {
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            for(int i=0;i<params.length;i++) {
                stmt.setObject(i+1, params[i]);
            }
            stmt.execute();
            DBResult res = DBResult.fromStatement(stmt);
            return res;
        }catch (SQLException e) {
            return new DBResult(e.getErrorCode(), e.getMessage());
        }
    }
    
    public static DBResult executeQuery(String query) {
        return executeQuery(query, new Object[0]);
    }

    public static void executeSQLFile(String nombre) {
        String sql = FileUtils.leerArchivo(nombre);
        if(sql != null) {
            executeQuery(sql, new Object[0]);
        }
    }
}
