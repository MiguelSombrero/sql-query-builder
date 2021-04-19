package database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class DatabaseTestBaseClass {
    private static Logger logger = LoggerFactory.getLogger(DatabaseTestBaseClass.class);

    private static final String dbDriver = "org.h2.Driver";

    protected void assertThatQueryIsValidSQL(String query) throws SQLException {
        executeQuery(query);
    }

    protected static void createTestDatabase() {
        try (Connection conn = DatabaseConnection.getConnection()) {
            Class.forName (dbDriver);
            conn.prepareStatement("RUNSCRIPT FROM 'src/test/resources/scripts/create-db.sql';").executeLargeUpdate();

        } catch (Exception ex) {
            logger.info(ex.getLocalizedMessage());
        }
    }

    protected static void insertDataToTestDatabase() {
        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.prepareStatement("INSERT INTO persons (id, firstname, lastname, age) VALUES (1, 'Miika', 'Somero', 39)").execute();

        } catch (Exception ex) {
            logger.info(ex.getLocalizedMessage());
        }
    }

    protected void executeQuery(String query) throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.prepareStatement(query).execute();
        }
    }
}
