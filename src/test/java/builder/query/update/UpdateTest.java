package builder.query.update;

import builder.query.SQLQueryBuilder;
import query.UpdateQuery;
import testutils.DatabaseConnection;
import testutils.DatabaseTestBaseClass;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;

import static builder.clause.ConditionClauseBuilder.valueOf;
import static org.junit.Assert.assertEquals;

public class UpdateTest extends DatabaseTestBaseClass {
    private SQLQueryBuilder sqlQueryBuilder;
    private FirstColumn baseQuery;

    @Before
    public void setUp() {
        initializeDatabase();
        sqlQueryBuilder = new SQLQueryBuilder(DatabaseConnection.getH2DataSource());

        this.baseQuery = sqlQueryBuilder
                .update()
                .table("person");
    }


    @Test
    public void testUpdateStringValue() throws SQLException {
        UpdateQuery query = this.baseQuery
                .column("firstname").setString("Miika-Lassi Kari")
                .build();

        assertEquals("UPDATE person SET firstname = 'Miika-Lassi Kari'", query.toString());

        int result = query.execute();

        assertEquals(3, result);
    }

    @Test
    public void testUpdateIntegerValue() throws SQLException {
        UpdateQuery query = this.baseQuery
                    .column("age").setInt(50)
                .build();

        assertEquals("UPDATE person SET age = 50", query.toString());

        int result = query.execute();

        assertEquals(3, result);
    }

    @Test
    public void testUpdateShortValue() throws SQLException {
        UpdateQuery query = this.sqlQueryBuilder
                .update()
                .table("all_types")
                .column("rating").setShort((short) 5)
                .build();

        assertEquals("UPDATE all_types SET rating = 5", query.toString());

        int result = query.execute();

        assertEquals(2, result);
    }

    @Test
    public void testUpdateLongValue() throws SQLException {
        UpdateQuery query = sqlQueryBuilder
                .update()
                .table("all_types")
                .column("hash").setLong(2485394539475834568L)
                .build();

        assertEquals("UPDATE all_types SET hash = 2485394539475834568", query.toString());

        int result = query.execute();

        assertEquals(2, result);
    }

    @Test
    public void testUpdateDoubleValue() throws SQLException {
        UpdateQuery query = this.baseQuery
                .column("age").setDouble(50.5)
                .build();

        assertEquals("UPDATE person SET age = 50.5", query.toString());

        int result = query.execute();

        assertEquals(3, result);
    }

    @Test
    public void testUpdateBigDecimalValue() throws SQLException {
        UpdateQuery query = this.sqlQueryBuilder
                .update()
                .table("all_types")
                .column("price").setBigDecimal(BigDecimal.valueOf(9000.10))
                .build();

        assertEquals("UPDATE all_types SET price = 9000.1", query.toString());

        int result = query.execute();

        assertEquals(2, result);
    }

    @Test
    public void testUpdateBooleanValue() throws SQLException {
        UpdateQuery query = sqlQueryBuilder
                .update()
                .table("all_types")
                .column("active").setBoolean(false)
                .build();

        assertEquals("UPDATE all_types SET active = false", query.toString());

        int result = query.execute();

        assertEquals(2, result);
    }

    @Test
    public void testUpdateDateValue() throws SQLException {
        UpdateQuery query = this.baseQuery
                .column("birthdate").setDate("1985-01-02")
                .build();

        assertEquals("UPDATE person SET birthdate = '1985-01-02'", query.toString());

        int result = query.execute();

        assertEquals(3, result);
    }

    @Test
    public void testUpdateTimestampValue() throws SQLException {
        UpdateQuery query = this.baseQuery
                .column("birthdate").setTimestamp("1985-01-02 21:04:11.123")
                .build();

        assertEquals("UPDATE person SET birthdate = '1985-01-02 21:04:11.123'", query.toString());

        int result = query.execute();

        assertEquals(3, result);
    }

    @Test
    public void testUpdateTimesValue() throws SQLException {
        UpdateQuery query = this.sqlQueryBuilder
                .update()
                .table("all_types")
                .column("clock").setTime("21:04:11")
                .build();

        assertEquals("UPDATE all_types SET clock = '21:04:11'", query.toString());

        int result = query.execute();

        assertEquals(2, result);
    }

    @Test
    public void testUpdateByteArrayValue() throws SQLException, IOException {
        byte[] file = readFileAsByteArray("files/byte-array-test-file.txt");

        UpdateQuery query = sqlQueryBuilder
                .update()
                .table("all_types")
                .column("contract").setByteArray(file)
                .build();

        int result = query.execute();

        assertEquals(2, result);
        assertThatQueryReturnsRows("SELECT * FROM all_types WHERE NOT contract = '74657374696e6720736f6d652076616c7565736f0a' AND NOT contract = '84657374696e6720736f6d652076616c7565736f0a'", 2);
    }

    @Test
    public void testUpdateMultipleValues() throws SQLException {
        UpdateQuery query = this.baseQuery
                    .column("firstname").setString("Miika")
                    .column("lastname").setString("Somero")
                    .column("age").setInt(50)
                .build();

        assertEquals("UPDATE person SET firstname = 'Miika', lastname = 'Somero', age = 50", query.toString());

        int result = query.execute();

        assertEquals(3, result);
    }

    @Test
    public void testUpdateWithCondition() throws SQLException {
        UpdateQuery query = this.baseQuery
                    .column("age").setInt(50)
                .where(valueOf("id").equalsInteger(1)
                        .or(valueOf("id").equalsInteger(2)))
                .build();

        assertEquals("UPDATE person SET age = 50 WHERE id = 1 OR id = 2", query.toString());

        int result = query.execute();

        assertEquals(2, result);
    }
}
