package query.dql;

import org.junit.Before;
import org.junit.Test;
import query.QueryFactory;
import testutils.DatabaseConnection;
import testutils.DatabaseTestBaseClass;

import java.sql.SQLException;
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
    public void testColumnCount() throws SQLException {
        List<Row> result = query.execute();

        Row firstRow = result.get(0);

        assertEquals(13, firstRow.getColumnCount());
    }

    @Test
    public void testGetIsCaseInsensitive() throws SQLException {
        List<Row> result = query.execute();

        Row firstRow = result.get(0);

        assertEquals("Taunus", firstRow.getString("model"));
        assertEquals("Taunus", firstRow.getString("MODEL"));
    }

    @Test
    public void testGetString() throws SQLException {
        List<Row> result = query.execute();

        Row firstRow = result.get(0);

        assertEquals("Taunus", firstRow.getString("model"));
    }

    @Test
    public void testGetInteger() throws SQLException {
        List<Row> result = query.execute();

        Row firstRow = result.get(0);

        assertEquals(9, firstRow.getInteger("ID"));
    }

    @Test(expected = ClassCastException.class)
    public void testCastWrongType() throws SQLException {
        List<Row> result = query.execute();

        Row firstRow = result.get(0);

        firstRow.getString("ID");
    }
}
