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
                .table("all_types")
                .build();
    }

    @Test
    public void testColumnNames() throws SQLException {
        List<Row> result = query.execute();

        Row firstRow = result.get(0);

        assertEquals("id, hash, age, newdate, newdatetime, created, active, country, model, brand, disclaimer, description, contract", firstRow.getColumnNames());
    }

    @Test
    public void testColumnName() throws SQLException {
        List<Row> result = query.execute();

        Row firstRow = result.get(0);

        assertEquals("id", firstRow.getColumnName(0));
        assertEquals("hash", firstRow.getColumnName(1));
        assertEquals("age", firstRow.getColumnName(2));
        assertEquals("newdate", firstRow.getColumnName(3));
        assertEquals("newdatetime", firstRow.getColumnName(4));
    }

    @Test
    public void testColumnType() throws SQLException {
        List<Row> result = query.execute();

        Row firstRow = result.get(0);

        assertEquals(Types.INTEGER, firstRow.getColumnType(0));
        assertEquals(Types.BIGINT, firstRow.getColumnType(1));
        assertEquals(Types.DOUBLE, firstRow.getColumnType(2));
        assertEquals(Types.DATE, firstRow.getColumnType(3));
        assertEquals(Types.TIMESTAMP, firstRow.getColumnType(4));
        assertEquals(Types.TIMESTAMP, firstRow.getColumnType(5));
        assertEquals(Types.BOOLEAN, firstRow.getColumnType(6));
        assertEquals(Types.CHAR, firstRow.getColumnType(7));
        assertEquals(Types.VARCHAR, firstRow.getColumnType(8));
        assertEquals(Types.VARCHAR, firstRow.getColumnType(12));
    }

    @Test
    public void testColumnCount() throws SQLException {
        List<Row> result = query.execute();

        Row firstRow = result.get(0);

        assertEquals(13, firstRow.getColumnCount());
    }

    @Test
    public void testGetStringFrom() throws SQLException {
        List<Row> result = query.execute();

        Row firstRow = result.get(0);

        assertEquals("Taunus", firstRow.getStringFrom(9));
    }

    @Test
    public void testGetIntegerFrom() throws SQLException {
        List<Row> result = query.execute();

        Row firstRow = result.get(0);

        assertEquals(8, firstRow.getIntegerFrom(0));
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
