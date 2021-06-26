package testutils;

import database.row.Row;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.List;

import static org.junit.Assert.assertEquals;

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
        try (Connection conn = DatabaseConnection.getH2Connection()) {
            Class.forName (dbDriver);
            String query = "RUNSCRIPT FROM '" + path + "';";
            conn.prepareStatement(query).executeLargeUpdate();

        } catch (Exception ex) {
            logger.info(ex.getLocalizedMessage());
        }
    }

    protected void assertRowCount(List<Row> result, int count) {
        assertEquals(count, result.size());
    }

    protected void assertColumnCount(List<Row> result, int count) {
        assertEquals(count, result.get(0).getColumnCount());
    }

    protected void assertThatQueryReturnsRows(String query, int expected) throws SQLException {
        int resultRows = executeSelectAndReturnRows(query);
        assertEquals(expected, resultRows);
    }

    protected int executeSelectAndReturnRows(String query) throws SQLException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        int rows;

        try (Connection conn = DatabaseConnection.getH2Connection()) {
            statement = conn.prepareStatement(query);
            resultSet = statement.executeQuery();
            resultSet.last();
            rows = resultSet.getRow();

        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
        }

        return rows;
    }

    public byte[] readFileAsByteArray(String path) throws IOException {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream(path);
        return new byte[in.available()];
    }
}
