package query;

import database.column.IntegerColumnValue;
import org.junit.Before;
import org.junit.Test;
import testutils.DatabaseConnection;
import testutils.DatabaseTestBaseClass;

import javax.sql.DataSource;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

public class DeleteQueryTest extends DatabaseTestBaseClass {
    private DataSource dataSource;

    @Before
    public void setUp() {
        initializeDatabase();
        dataSource = DatabaseConnection.getH2DataSource();
    }

    @Test
    public void testExecuteDelete() throws SQLException {
        StringBuilder queryString = new StringBuilder("DELETE FROM all_types WHERE id = 12");
        DeleteQuery query = new DeleteQuery(new SQLStatement(queryString), dataSource);

        int result = query.execute();
        assertEquals(1, result);

        assertThatQueryReturnsRows("SELECT * FROM all_types WHERE id = 12", 0);
    }

    @Test
    public void testExecuteParametrizedDelete() throws SQLException {
        StringBuilder queryString = new StringBuilder("DELETE FROM all_types WHERE id = ");
        Statement statement = new SQLStatement(queryString);

        IntegerColumnValue param = new IntegerColumnValue(12);
        statement.addParam(param);

        DeleteQuery query = new DeleteQuery(statement, dataSource);

        int result = query.execute();
        assertEquals(1, result);

        assertThatQueryReturnsRows("SELECT * FROM all_types WHERE id = 12", 0);
    }
}
