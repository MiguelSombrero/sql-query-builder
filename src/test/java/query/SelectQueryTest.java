package query;

import database.column.TimestampColumnValue;
import database.row.Row;
import org.junit.Before;
import org.junit.Test;
import testutils.DatabaseConnection;
import testutils.DatabaseTestBaseClass;

import javax.sql.DataSource;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class SelectQueryTest extends DatabaseTestBaseClass {
    private DataSource dataSource;

    @Before
    public void setUp() {
        initializeDatabase();
        dataSource = DatabaseConnection.getH2DataSource();
    }

    @Test
    public void testExecuteAllFieldsQuery() throws SQLException {
        StringBuilder queryString = new StringBuilder("SELECT * FROM all_types");
        SelectQuery query = new SelectQuery(new SQLClause(queryString), dataSource);

        List<Row> result = query.execute();

        Row firstRow = result.get(0);

        assertEquals(12, firstRow.getInteger("id"));
        assertEquals(9223372036854775806L, firstRow.getLong("hash"));
        assertEquals(25.6, firstRow.getDouble("age"), 0.1);
        assertEquals(13200.50, firstRow.getBigDecimal("price").doubleValue(), 0.01);
        assertEquals(3.4, firstRow.getBigDecimal("taxes").doubleValue(), 0.01);
        assertEquals("2020-02-02", firstRow.getDate("newdate").toString());
        assertEquals("2020-02-02", firstRow.getDate("newdate").toString());
        assertEquals("22:02:01", firstRow.getTime("clock").toString());
        assertEquals(true, firstRow.getBoolean("active"));
        assertEquals("23", firstRow.getString("country"));
        assertEquals("Taunus", firstRow.getString("model"));
        assertEquals("Ford", firstRow.getString("brand"));
        assertEquals("Does not work", firstRow.getString("description"));
        assertEquals("testing some values\n", new String(firstRow.getBytes("contract"), StandardCharsets.UTF_8));
    }

    @Test
    public void testExecuteParametrizedQuery() throws SQLException {
        StringBuilder queryString = new StringBuilder("SELECT * FROM person WHERE birthdate > ?");
        Clause clause = new SQLClause(queryString);

        TimestampColumnValue param = new TimestampColumnValue(Timestamp.valueOf("1980-02-28 21:00:00"));
        clause.addParam(param);

        SelectQuery query = new SelectQuery(clause, dataSource);

        List<Row> result = query.execute();

        Row firstRow = result.get(0);

        assertEquals(3, firstRow.getInteger("id"));
        assertEquals(35, firstRow.getInteger("age"));
        assertEquals("Maija", firstRow.getString("firstname"));
        assertEquals("Kultanen", firstRow.getString("lastname"));
    }

    @Test
    public void testExecuteQueryWithNullReturnValues() throws SQLException {
        StringBuilder queryString = new StringBuilder("SELECT * FROM school WHERE id = 9");
        SelectQuery query = new SelectQuery(new SQLClause(queryString), dataSource);

        List<Row> result = query.execute();

        Row firstRow = result.get(0);

        assertEquals(null, firstRow.getString("name"));
    }

    @Test
    public void testQueryCanBeExecutedMultipleTime() throws SQLException {
        StringBuilder queryString = new StringBuilder("SELECT * FROM person");
        SelectQuery query = new SelectQuery(new SQLClause(queryString), dataSource);

        List<Row> result1 = query.execute();
        assertRowCount(result1, 3);

        List<Row> result2 = query.execute();
        assertRowCount(result2, 3);

        List<Row> result3 = query.execute();
        assertRowCount(result3, 3);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testExecuteInvalidQueryThrowsSQLException() throws SQLException {
        StringBuilder queryString = new StringBuilder("SELECT * FROM person WHERE birthdate > ?");
        SelectQuery query = new SelectQuery(new SQLClause(queryString), dataSource);
        query.execute();
    }
}
