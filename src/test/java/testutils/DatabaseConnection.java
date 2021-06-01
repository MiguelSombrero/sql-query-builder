package testutils;

import org.h2.jdbcx.JdbcDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String dbPath = "jdbc:h2:mem:test;MODE=MYSQL;DATABASE_TO_LOWER=TRUE;DB_CLOSE_DELAY=-1;";
    private static final String dbUser = "sa";
    private static final String dbPassword = "";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(dbPath, dbUser, dbPassword);
    }

    public static DataSource getDataSource() {
        JdbcDataSource datasource = new JdbcDataSource();
        datasource.setURL(dbPath);
        datasource.setUser(dbUser);
        datasource.setPassword(dbPassword);
        return datasource;
    }

}
