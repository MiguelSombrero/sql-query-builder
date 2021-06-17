package query.dml;

import database.column.IntegerColumnValue;
import database.column.StringColumnValue;
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
        dataSource = DatabaseConnection.getDataSource();
    }

    @Test
    public void testExecuteDelete() throws SQLException {
        StringBuilder queryString = new StringBuilder("DELETE FROM all_types WHERE id = 12");
        DeleteQuery query = new DeleteQuery(queryString, dataSource);

        int result = query.execute();
        assertEquals(1, result);

        assertThatQueryReturnsRows("SELECT * FROM all_types WHERE id = 12", 0);
    }

    @Test
    public void testExecuteParametrizedDelete() throws SQLException {
        StringBuilder queryString = new StringBuilder("DELETE FROM all_types WHERE id = ?");
        DeleteQuery query = new DeleteQuery(queryString, dataSource);

        IntegerColumnValue param = new IntegerColumnValue(12);
        query.addParam(param);

        int result = query.execute();
        assertEquals(1, result);

        assertThatQueryReturnsRows("SELECT * FROM all_types WHERE id = 12", 0);
    }
}
