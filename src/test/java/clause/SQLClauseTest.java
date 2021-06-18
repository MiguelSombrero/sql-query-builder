package clause;

import database.column.StringColumnValue;
import org.junit.Before;
import org.junit.Test;
import database.row.Row;
import query.dql.SelectQuery;
import testutils.DatabaseConnection;
import testutils.DatabaseTestBaseClass;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class SQLClauseTest extends DatabaseTestBaseClass {
    private DataSource dataSource;

    @Before
    public void setUp() {
        initializeDatabase();
        dataSource = DatabaseConnection.getDataSource();
    }

    @Test
    public void testAppend() {
        SQLClause query = new SQLClause(new StringBuilder());
        query.append("miika");
        query.append(" ");
        query.append("Somero");
        assertEquals("miika Somero", query.toString());
    }

    @Test
    public void testAppendFront() {
        SQLClause query = new SQLClause(new StringBuilder());
        query.appendFront("somero");
        query.appendFront("miika ");
        query.appendFront("testaaja ");
        assertEquals("testaaja miika somero", query.toString());
    }

    @Test
    public void testMergeSubQuery() throws SQLException {
        StringBuilder queryString = new StringBuilder("SELECT * FROM person WHERE ");
        StringBuilder subQueryString = new StringBuilder("firstname = ?");

        SelectQuery selectQuery = new SelectQuery(queryString, dataSource);
        SQLClause subQuery = new SQLClause(subQueryString);
        StringColumnValue param = new StringColumnValue("Miika");
        subQuery.addParam(param);

        selectQuery.mergeSubQuery(subQuery);

        assertEquals("SELECT * FROM person WHERE firstname = 'Miika'", selectQuery.toString());

        List<Row> result = selectQuery.execute();

        assertRowCount(result, 1);
        assertColumnCount(result, 5);
    }
}
