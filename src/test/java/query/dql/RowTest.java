package query.dql;

import builder.clause.WhereClauseFactory;
import database.row.Row;
import org.junit.Before;
import org.junit.Test;
import query.QueryFactory;
import testutils.DatabaseConnection;
import testutils.DatabaseTestBaseClass;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

public class RowTest extends DatabaseTestBaseClass {
    private QueryFactory queryFactory;
    private SelectQuery baseQuery;

    @Before
    public void setUpQuery() {
        initializeDatabase();
        queryFactory = new QueryFactory(DatabaseConnection.getDataSource());

        this.baseQuery = queryFactory
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
        SelectQuery query = queryFactory
                .select()
                    .column("person.id").alias("personId")
                    .column("course.id").alias("courseId")
                .from()
                    .table("person")
                .leftJoin("course").on("person.id", "course.person_id")
                .where(WhereClauseFactory.valueOf("firstname").equals("Miika"))
                .build();

        List<Row> result = query.execute();

        Row firstRow = result.get(0);

        assertColumnCount(result, 2);
        assertEquals(1, firstRow.getInteger("personId"));
        assertEquals(8, firstRow.getInteger("courseId"));
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

        assertEquals(9, firstRow.getInteger("ID"));
    }

    @Test
    public void testGetBigInteger() throws SQLException {
        List<Row> result = baseQuery.execute();

        Row firstRow = result.get(0);

        BigInteger expectedInteger = new BigInteger("9223372036854775806");

        assertEquals(expectedInteger, firstRow.getBigInteger("hash"));
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

        byte[] expected = "This is car".getBytes(StandardCharsets.UTF_8);

        assertThat(expected, equalTo(firstRow.getBytes("description")));
    }

    // how to test Blob equals?
    /*@Test
    public void testGetBlob() throws SQLException {
        List<Row> result = baseQuery.execute();

        Row firstRow = result.get(0);

        byte[] bytesExpected = "74657374696e6720736f6d652076616c7565736f0a".getBytes(StandardCharsets.UTF_8);

        Connection connection = DatabaseConnection.getConnection();

        Blob expected = connection.createBlob();

        expected.setBytes(1, bytesExpected);

        assertThat(expected.getBytes(1, 20), equalTo(firstRow.getBlob("contract").getBytes(1, 20)));
    }*/

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
        SelectQuery query = queryFactory
                .select()
                    .all()
                .from()
                    .table("person")
                    .table("address")
                .build();

        query.execute();
    }

}
