package query;

import org.junit.Before;
import org.junit.Test;
import query.CreateQuery;
import testutils.DatabaseConnection;
import testutils.DatabaseTestBaseClass;

import javax.sql.DataSource;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

public class CreateQueryTest extends DatabaseTestBaseClass {
    private DataSource dataSource;

    @Before
    public void setUp() {
        initializeDatabase();
        dataSource = DatabaseConnection.getDataSource();
    }

    @Test
    public void testExecuteAllFieldsCreateTable() throws SQLException {
        StringBuilder queryString = new StringBuilder("CREATE TABLE cars (ID INT, hash BIGINT, age DOUBLE, date DATE, datetime DATETIME, created TIMESTAMP, active BOOLEAN, country CHAR, model VARCHAR(32), brand VARCHAR(64), disclaimer VARCHAR(128), description VARCHAR(255), contract BLOB)");
        CreateQuery query = new CreateQuery(queryString, dataSource);

        query.execute();

        assertThatQueryReturnsRows("SELECT * FROM cars", 0);
    }
}
