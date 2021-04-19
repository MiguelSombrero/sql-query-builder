package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String dbPath = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
    private static final String dbUser = "sa";
    private static final String dbPassword = "";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(dbPath, dbUser, dbPassword);
    }

}
