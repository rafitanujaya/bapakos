package Config;

import java.sql.Connection;
import java.sql.SQLException;

public class DBConfig {
    private final DatabaseConnector connector;

    public DBConfig() throws SQLException {
        String type = EnvLoader.get("DB_TYPE");
        switch (type.toLowerCase()) {
            case "mysql":
                connector = new MySQLConnector();
                break;
            default:
                throw new SQLException("Tipe database tidak didukung: " + type);
        }
    }

    public Connection getConnection() throws SQLException {
        return connector.getConnection();
    }
}
