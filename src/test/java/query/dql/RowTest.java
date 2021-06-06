package query.dql;

import org.junit.Before;
import org.junit.Test;
import query.QueryFactory;
import testutils.DatabaseConnection;
import testutils.DatabaseTestBaseClass;

import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class RowTest extends DatabaseTestBaseClass {
    private QueryFactory queryFactory;
    private SelectQuery query;

    @Before
    public void setUpQuery() {
        initializeDatabase();
        queryFactory = new QueryFactory(DatabaseConnection.getDataSource());

        this.query = queryFactory
                .select()
                .all()
                .from()
                .table("person")
                .build();
    }

    @Test
    public void testColumnNames() throws SQLException {
        List<Row> result = query.execute();

        Row firstRow = result.get(0);

        assertEquals("id, birthdate, firstname, lastname, age", firstRow.getColumnNames());
    }

    @Test
    public void testColumnName() throws SQLException {
        List<Row> result = query.execute();

        Row firstRow = result.get(0);

        assertEquals("id", firstRow.getColumnName(0));
        assertEquals("birthdate", firstRow.getColumnName(1));
        assertEquals("firstname", firstRow.getColumnName(2));
        assertEquals("lastname", firstRow.getColumnName(3));
        assertEquals("age", firstRow.getColumnName(4));
    }

    @Test
    public void testColumnType() throws SQLException {
        List<Row> result = query.execute();

        Row firstRow = result.get(0);

        assertEquals(Types.INTEGER, firstRow.getColumnType(0));
        assertEquals(Types.TIMESTAMP, firstRow.getColumnType(1));
        assertEquals(Types.VARCHAR, firstRow.getColumnType(2));
        assertEquals(Types.VARCHAR, firstRow.getColumnType(3));
        assertEquals(Types.INTEGER, firstRow.getColumnType(4));
    }

    @Test
    public void testColumnCount() throws SQLException {
        List<Row> result = query.execute();

        Row firstRow = result.get(0);

        assertEquals(5, firstRow.getColumnCount());
    }

    @Test
    public void testGetStringFrom() throws SQLException {
        List<Row> result = query.execute();

        Row firstRow = result.get(0);

        assertEquals("Miika", firstRow.getStringFrom(2));
    }

    @Test
    public void testGetIntegerFrom() throws SQLException {
        List<Row> result = query.execute();

        Row firstRow = result.get(0);

        assertEquals(1, firstRow.getIntegerFrom(0));
    }

    @Test(expected = ClassCastException.class)
    public void testCastWrongType() throws SQLException {
        List<Row> result = query.execute();

        Row firstRow = result.get(0);

        firstRow.getStringFrom(0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetFromOutOfBoundIndex() throws SQLException {
        List<Row> result = query.execute();

        Row firstRow = result.get(0);

        firstRow.getStringFrom(100);
    }
}
