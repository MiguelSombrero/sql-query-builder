package query;

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

public class SQLQueryTest extends DatabaseTestBaseClass {
    private DataSource dataSource;

    @Before
    public void setUp() {
        initializeDatabase();
        dataSource = DatabaseConnection.getDataSource();
    }

    @Test
    public void testAppendString() {
        SQLQuery query = new SQLQuery(new StringBuilder());
        query.append("miika");
        query.append(" ");
        query.append("Somero");
        assertEquals("miika Somero", query.toString());
    }

    @Test
    public void testAppendInteger() {
        SQLQuery query = new SQLQuery(new StringBuilder());
        query.append(0);
        query.append(1);
        query.append(2);
        assertEquals("012", query.toString());
    }

    @Test
    public void testAppendDouble() {
        SQLQuery query = new SQLQuery(new StringBuilder());
        query.append(0.0);
        query.append(1.1);
        query.append(2.2);
        assertEquals("0.01.12.2", query.toString());
    }

    @Test
    public void testInsert() {
        SQLQuery query = new SQLQuery(new StringBuilder());
        query.insert(0, "somero");
        query.insert(0, "miika ");
        query.insert(6, "testaaja ");
        assertEquals("miika testaaja somero", query.toString());
    }

    @Test
    public void testMergeSubQuery() throws SQLException {
        StringBuilder queryString = new StringBuilder("SELECT * FROM person WHERE ");
        StringBuilder subQueryString = new StringBuilder("firstname = ?");

        SelectQuery selectQuery = new SelectQuery(queryString, dataSource);
        SQLQuery subQuery = new SQLQuery(subQueryString);
        StringColumnValue param = new StringColumnValue("Miika");
        subQuery.addParam(param);

        selectQuery.mergeSubQuery(subQuery);

        assertEquals("SELECT * FROM person WHERE firstname = 'Miika'", selectQuery.toString());

        List<Row> result = selectQuery.execute();

        assertRowCount(result, 1);
        assertColumnCount(result, 5);
    }
}
