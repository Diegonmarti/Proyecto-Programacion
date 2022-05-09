package proyectotercera;

import proyectotercera.utils.ISerializable;

public class Config implements ISerializable {

    // Desde FileUtils.RUTA_BASE
    private static final String RUTA_CONFIG = "config\\";
    private static final String NOMBRE_CONFIG = "settings.cfg";

    // Valores por defecto de los ajustes
    private static final String DEFAULT_DB_ADDRESS = "localhost";
    private static final String DEFAULT_DB_USERNAME = "root";
    private static final String DEFAULT_DB_PASSWORD = "";
    private static final String DEFAULT_DBNAME = "horarios";
    private static final String DEFAULT_DB_SQLPATH = RUTA_CONFIG + "create.sql";
    private static final boolean DEFAULT_ALLOW_GUESTS = true;

    private static final String DB_PREFIX = "db.";
    private static final String LOGIN_PREFIX = "login.";

    // Ajustes de la Base de Datos
    private static String address = DEFAULT_DB_ADDRESS;
    private static String username = DEFAULT_DB_USERNAME;
    private static String password = DEFAULT_DB_PASSWORD;
    private static String dbname = DEFAULT_DBNAME;
    private static String sqlPath = DEFAULT_DB_SQLPATH;

    // Ajustes del login
    private static boolean allowGuests = DEFAULT_ALLOW_GUESTS;
    

    // Guardar una instancia de la clase para llamar a los métodos de ISerializable
    public static final Config INSTANCIA = new Config();

    private Config() {}
    

    public static String getAddress() {
        return address;
    }

    public static String getUsername() {
        return username;
    }

    public static String getPassword() {
        return password;
    }

    public static String getDBName() {
        return dbname;
    }

    public static String getSqlPath() {
        return sqlPath;
    }
    
    public static boolean getAllowGuests() {
        return allowGuests;
    }

    public static String getConfigFilePath() {
        return RUTA_CONFIG + NOMBRE_CONFIG;
    }

    @Override
    public String toSerializedData() {
        String out = "";
        out += "// La direccion del mysql al que conectarnos (default: " + DEFAULT_DB_ADDRESS + ")\n";
        out += DB_PREFIX + "address=" + address + "\n";
        out += "// Los credenciales con los que conectarnos\n";
        out += "// Usuario (default: " + DEFAULT_DB_USERNAME + ")\n";
        out += DB_PREFIX + "username=" + username + "\n";
        out += "// Password (default: " + DEFAULT_DB_PASSWORD + ")\n";
        out += DB_PREFIX + "password=" + password + "\n";
        out += "// La base de datos a la que conectarnos (default: " + DEFAULT_DBNAME + ")\n";
        out += DB_PREFIX + "dbname=" + dbname + "\n";
        out += "// La ruta al archivo .sql al que llamar si no existe la base de datos para crear la estructura de tablas (default: " + DEFAULT_DB_SQLPATH + ")\n";
        out += DB_PREFIX + "sqlfile=" + sqlPath + "\n";
        out += "// Permitir o no la entrada como invitado (default: " + DEFAULT_ALLOW_GUESTS + ")\n";
        out += LOGIN_PREFIX + "allow_guests=" + allowGuests + "\n";
        return out;
    }

    @Override
    public int fromSerializedData(String[] data) {
        for(String line : data) {
            if(line.startsWith("//")) continue;

            if(line.startsWith(DB_PREFIX)) {
                // Coge desde el final del prefijo "db." hasta el "=". Por ejemplo de "db.address=aa" coge "address"
                String clave = line.substring(DB_PREFIX.length(), line.indexOf("="));
                // Coge solo el valor. Usando el ejemplo de arriba, cogería "aa"
                String valor = line.substring(line.indexOf("=") + 1);
                switch(clave) {
                    case "address":
                        address = valor;
                        break;
                    case "username":
                        username = valor;
                        break;
                    case "password":
                        password = valor;
                        break;
                    case "dbname":
                        dbname = valor;
                        break;
                    case "sqlfile":
                        sqlPath = valor;
                        break;
                    default:
                        break;
                }
            }

            if(line.startsWith(LOGIN_PREFIX)) {
                String clave = line.substring(LOGIN_PREFIX.length(), line.indexOf("="));
                String valor = line.substring(line.indexOf("=") + 1);
                switch(clave) {
                    case "allow_guests":
                        allowGuests = Boolean.parseBoolean(valor);
                        break;
                    default:
                        break;
                }
            }
        }
        return data.length;
    }
}
