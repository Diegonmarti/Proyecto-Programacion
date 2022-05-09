package proyectotercera.utils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class DBResult {
    private ResultSet rs;
    private ResultSetMetaData rsMetadata;
    private int updateCount;
    private int errorCode;
    private String errorMessage;
    
    public DBResult(int errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
    
    private DBResult(ResultSet rs, ResultSetMetaData rsMetadata, int updateCount) {
        this.rs = rs;
        this.rsMetadata = rsMetadata;
        this.updateCount = updateCount;
        this.next(); // Para que el primer get que se haga no sea null
    }
    
    public static DBResult fromStatement(PreparedStatement stmt) throws SQLException {
        return new DBResult(stmt.getResultSet(), stmt.getMetaData(), stmt.getUpdateCount());
    }

    // Si errorCode es 0, no hay error, retorna false, si no es 0, hubo un error, retorna true
    public boolean isError() {
        return this.errorCode != 0;
    }

    public int getUpdateCount() {
        return updateCount;
    }
    
    public boolean next() {
        try {
            return rs.next();
        }catch(SQLException | NullPointerException e) {
            return false;
        }
    }
    
    public Object get(int col) {
        try {
            return rs.getObject(col);
        }catch(SQLException e) {
            return null;
        }
    }
    
    public Object get(String colId) {
        try {
            return rs.getObject(colId);
        }catch(SQLException e) {
            return null;
        }
    }
    
    public String getColumnName(int col) {
        try {
            return rsMetadata.getCatalogName(col);
        }catch(SQLException e) {
            return null;
        }
    }
}
