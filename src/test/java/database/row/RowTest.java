package database.row;

import builder.clause.ConditionClauseBuilder;
import builder.query.SQLQueryBuilder;
import org.junit.Before;
import org.junit.Test;
import query.SelectQuery;
import testutils.DatabaseConnection;
import testutils.DatabaseTestBaseClass;

import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.*;

public class RowTest extends DatabaseTestBaseClass {
    private SQLQueryBuilder sqlQueryBuilder;
    private SelectQuery baseQuery;

    @Before
    public void setUpQuery() {
        initializeDatabase();
        sqlQueryBuilder = new SQLQueryBuilder(DatabaseConnection.getDataSource());

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

        assertEquals(13, firstRow.getColumnCount());
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
    public void testGetLong() throws SQLException {
        List<Row> result = baseQuery.execute();

        Row firstRow = result.get(0);

        assertEquals(9223372036854775806L, firstRow.getLong("hash"));
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
    public void testGetDouble() throws SQLException {
        List<Row> result = baseQuery.execute();

        Row firstRow = result.get(0);

        assertEquals(25.6, firstRow.getDouble("age"), 0.1);
    }

    @Test
    public void testGetBoolean() throws SQLException {
        List<Row> result = baseQuery.execute();

        Row firstRow = result.get(0);

        assertTrue(firstRow.getBoolean("active"));
    }

    @Test
    public void testGetLocalDate() throws SQLException {
        List<Row> result = baseQuery.execute();

        Row firstRow = result.get(0);

        assertEquals(LocalDate.parse("2020-02-02"), firstRow.getLocalDate("newdate"));
    }

    @Test
    public void testGetLocalDateTimeOfDateTime() throws SQLException {
        List<Row> result = baseQuery.execute();

        Row firstRow = result.get(0);

        assertEquals(LocalDateTime.of(2020, 02, 02, 22, 02, 01), firstRow.getLocalDateTime("newdatetime"));
    }

    @Test
    public void testGetLocalDateTimeOfTimestamp() throws SQLException {
        List<Row> result = baseQuery.execute();

        Row firstRow = result.get(0);

        assertEquals(LocalDateTime.of(2020, 02, 02, 22, 02, 01), firstRow.getLocalDateTime("created"));
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
