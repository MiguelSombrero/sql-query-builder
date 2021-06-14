package query.dql;

import database.column.DateTimeColumnValue;
import database.column.StringColumnValue;
import database.row.Row;
import org.junit.Before;
import org.junit.Test;
import query.dml.InsertQuery;
import testutils.DatabaseConnection;
import testutils.DatabaseTestBaseClass;

import javax.sql.DataSource;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class SelectQueryTest extends DatabaseTestBaseClass {
    private DataSource dataSource;

    @Before
    public void setUp() {
        initializeDatabase();
        dataSource = DatabaseConnection.getDataSource();
    }

    @Test
    public void testExecuteAllFieldsQuery() throws SQLException {
        StringBuilder queryString = new StringBuilder("SELECT * FROM all_types");
        SelectQuery query = new SelectQuery(queryString, dataSource);

        List<Row> result = query.execute();

        Row firstRow = result.get(0);

        assertEquals(12, firstRow.getInteger("id"));
        assertEquals(9223372036854775806L, firstRow.getLong("hash"));
        assertEquals(25.6, firstRow.getDouble("age"), 0.1);
        assertEquals("2020-02-02", firstRow.getLocalDate("newdate").toString());
        assertEquals("2020-02-02T22:02:01", firstRow.getLocalDateTime("newdatetime").toString());
        assertEquals("2020-02-02T22:02:01", firstRow.getLocalDateTime("created").toString());
        assertEquals(true, firstRow.getBoolean("active"));
        assertEquals(String.valueOf(23), firstRow.getString("country"));
        assertEquals("Taunus", firstRow.getString("model"));
        assertEquals("Ford", firstRow.getString("brand"));
        assertEquals("Does not work", firstRow.getString("disclaimer"));
        assertEquals("This is car", firstRow.getString("description"));
        assertEquals("testing some values\n", new String(firstRow.getBytes("contract"), StandardCharsets.UTF_8));
    }

    @Test
    public void testExecuteParametrizedQuery() throws SQLException {
        StringBuilder queryString = new StringBuilder("SELECT * FROM person WHERE birthdate > ?");
        SelectQuery query = new SelectQuery(queryString, dataSource);

        LocalDateTime birthdate = LocalDateTime.parse("1980-02-28 21:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        DateTimeColumnValue param = new DateTimeColumnValue(birthdate);
        query.addParam(param);

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
        SelectQuery query = new SelectQuery(queryString, dataSource);

        List<Row> result = query.execute();

        Row firstRow = result.get(0);

        assertEquals(null, firstRow.getString("name"));
    }
}
