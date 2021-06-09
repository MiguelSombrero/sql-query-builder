package query;

import org.junit.Before;
import org.junit.Test;
import query.dql.Row;
import query.dql.SelectQuery;
import testutils.DatabaseConnection;
import testutils.DatabaseTestBaseClass;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class SQLQueryTest extends DatabaseTestBaseClass {
    private DataSource dataSource;

    @Before
    public void setUp() {
        initializeDatabase();
        dataSource = DatabaseConnection.getDataSource();
    }

    @Test
    public void testMergeSubQuery() throws SQLException {
        StringBuilder queryString = new StringBuilder("SELECT * FROM person WHERE ");
        StringBuilder subQueryString = new StringBuilder("firstname = ?");

        SelectQuery selectQuery = new SelectQuery(queryString, dataSource);
        SQLQuery subQuery = new SQLQuery(subQueryString);
        subQuery.addParam("Miika");

        selectQuery.mergeSubQuery(subQuery);

        assertEquals("SELECT * FROM person WHERE firstname = 'Miika'", selectQuery.toString());

        List<Row> result = selectQuery.execute();

        assertRowCount(result, 1);
        assertColumnCount(result, 5);
    }
}
