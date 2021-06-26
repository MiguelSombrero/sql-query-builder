package database.row;

import builder.clause.ConditionClauseBuilder;
import builder.query.SQLQueryBuilder;
import org.junit.Before;
import org.junit.Test;
import query.SelectQuery;
import testutils.DatabaseConnection;
import testutils.DatabaseTestBaseClass;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

import static org.junit.Assert.*;

public class RowTest extends DatabaseTestBaseClass {
    private SQLQueryBuilder sqlQueryBuilder;
    private SelectQuery baseQuery;

    @Before
    public void setUpQuery() {
        initializeDatabase();
        sqlQueryBuilder = new SQLQueryBuilder(DatabaseConnection.getH2DataSource());

        this.baseQuery = sqlQueryBuilder
                .select()
                .all()
                .from()
                .table("all_types")
                .build();
    }

    @Test
    public void testColumnCount() throws SQLException {
        List<Row> result = baseQuery.execute();

        Row firstRow = result.get(0);

        assertEquals(15, firstRow.getColumnCount());
    }

    @Test
    public void testGetIsCaseInsensitive() throws SQLException {
        List<Row> result = baseQuery.execute();

        Row firstRow = result.get(0);

        assertEquals("Taunus", firstRow.getString("model"));
        assertEquals("Taunus", firstRow.getString("MODEL"));
    }

    @Test
    public void testUsesAliasNamesInsteadOfColumnNames() throws SQLException {
        SelectQuery query = sqlQueryBuilder
                .select()
                    .column("person.id").alias("personId")
                    .column("course.id").alias("courseId")
                .from()
                    .table("person")
                .leftJoin("course").on("person.id", "course.person_id")
                .where(ConditionClauseBuilder.valueOf("firstname").equalsString("Miika"))
                .build();

        List<Row> result = query.execute();

        Row firstRow = result.get(0);

        assertColumnCount(result, 2);
        assertEquals(1, firstRow.getInteger("personId"));
        assertEquals(11, firstRow.getInteger("courseId"));
    }

    @Test
    public void testGetString() throws SQLException {
        List<Row> result = baseQuery.execute();

        Row firstRow = result.get(0);

        assertEquals("Taunus", firstRow.getString("model"));
    }

    @Test
    public void testGetInteger() throws SQLException {
        List<Row> result = baseQuery.execute();

        Row firstRow = result.get(0);

        assertEquals(12, firstRow.getInteger("ID"));
    }

    @Test
    public void testGetShort() throws SQLException {
        List<Row> result = baseQuery.execute();

        Row firstRow = result.get(0);

        assertEquals(8, firstRow.getShort("rating"));
    }

    @Test
    public void testGetLong() throws SQLException {
        List<Row> result = baseQuery.execute();

        Row firstRow = result.get(0);

        assertEquals(9223372036854775806L, firstRow.getLong("hash"));
    }

    @Test
    public void testGetDouble() throws SQLException {
        List<Row> result = baseQuery.execute();

        Row firstRow = result.get(0);

        assertEquals(25.6, firstRow.getDouble("age"), 0.1);
    }

    @Test
    public void testGetBigDecimal() throws SQLException {
        List<Row> result = baseQuery.execute();

        Row firstRow = result.get(0);

        assertEquals(BigDecimal.valueOf(13200.50).doubleValue(), firstRow.getBigDecimal("price").doubleValue(), 0.01);
    }

    @Test
    public void testGetBytes() throws SQLException {
        List<Row> result = baseQuery.execute();

        Row firstRow = result.get(0);

        String expected = "testing some values\n";
        byte[] bytes = firstRow.getBytes("contract");

        assertEquals(expected, new String(bytes, StandardCharsets.UTF_8));
    }

    @Test
    public void testGetBoolean() throws SQLException {
        List<Row> result = baseQuery.execute();

        Row firstRow = result.get(0);

        assertTrue(firstRow.getBoolean("active"));
    }

    @Test
    public void testGetDate() throws SQLException {
        List<Row> result = baseQuery.execute();

        Row firstRow = result.get(0);

        assertEquals(Date.valueOf("2020-02-02"), firstRow.getDate("newdate"));
    }

    @Test
    public void testGetTime() throws SQLException {
        List<Row> result = baseQuery.execute();

        Row firstRow = result.get(0);

        assertEquals(Time.valueOf("22:02:01"), firstRow.getTime("clock"));
    }

    @Test
    public void testGetTimestamp() throws SQLException {
        List<Row> result = baseQuery.execute();

        Row firstRow = result.get(0);

        assertEquals(Timestamp.valueOf("2020-02-02 22:02:01.0"), firstRow.getTimestamp("created"));
    }

    @Test(expected = ClassCastException.class)
    public void testCastWrongType() throws SQLException {
        List<Row> result = baseQuery.execute();

        Row firstRow = result.get(0);

        firstRow.getString("ID");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDuplicateKeyThrowsException() throws SQLException {
        SelectQuery query = sqlQueryBuilder
                .select()
                    .all()
                .from()
                    .table("person")
                    .table("address")
                .build();

        query.execute();
    }

    @Test(expected = NullPointerException.class)
    public void testKeyNotFoundThrowsException() throws SQLException {
        List<Row> result = baseQuery.execute();

        Row firstRow = result.get(0);

        firstRow.getString("notfound");
    }

}
