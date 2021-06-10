package query.dml;

import database.column.StringColumnValue;
import org.junit.Before;
import org.junit.Test;
import testutils.DatabaseConnection;
import testutils.DatabaseTestBaseClass;

import javax.sql.DataSource;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

public class InsertQueryTest extends DatabaseTestBaseClass {
    private DataSource dataSource;

    @Before
    public void setUp() {
        initializeDatabase();
        dataSource = DatabaseConnection.getDataSource();
    }

    @Test
    public void testExecuteInsert() throws SQLException {
        StringBuilder queryString = new StringBuilder("INSERT INTO person (firstname, lastname) VALUES (?, ?)");
        InsertQuery query = new InsertQuery(queryString, dataSource);

        StringColumnValue param1 = new StringColumnValue("Miika");
        StringColumnValue param2 = new StringColumnValue("Kukkonen");
        query.addParam(param1);
        query.addParam(param2);

        assertEquals("INSERT INTO person (firstname, lastname) VALUES ('Miika', 'Kukkonen')", query.toString());

        int result = query.execute();

        assertEquals(4, result);
    }
}
