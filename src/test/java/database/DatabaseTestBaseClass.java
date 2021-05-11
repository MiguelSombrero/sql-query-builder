package database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class DatabaseTestBaseClass {
    protected static Logger logger = LoggerFactory.getLogger(DatabaseTestBaseClass.class);

    private static final String dbDriver = "org.h2.Driver";
    private static final String DROP_DB_SCRIPT = "src/test/resources/scripts/drop-db.sql";
    private static final String CREATE_DB_SCRIPT = "src/test/resources/scripts/create-db.sql";
    private static final String CREATE_DATA_SCRIPT = "src/test/resources/scripts/create-data.sql";

    protected void initializeDatabase() {
        dropTestDatabase();
        createTestDatabase();
        insertDataToTestDatabase();
    }

    private static void dropTestDatabase() {
        runScriptFrom(DROP_DB_SCRIPT);
    }

    private static void createTestDatabase() {
        runScriptFrom(CREATE_DB_SCRIPT);
    }

    private static void insertDataToTestDatabase() {
        runScriptFrom(CREATE_DATA_SCRIPT);
    }

    private static void runScriptFrom(String path) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            Class.forName (dbDriver);
            String query = "RUNSCRIPT FROM '" + path + "';";
            conn.prepareStatement(query).executeLargeUpdate();

        } catch (Exception ex) {
            logger.info(ex.getLocalizedMessage());
        }
    }

    protected void assertThatQueryIsValidSQL(String query) throws SQLException {
        execute(query);
    }

    protected void execute(String query) throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.prepareStatement(query).execute();
        }
    }
}
