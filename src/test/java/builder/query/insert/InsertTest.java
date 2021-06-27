package builder.query.insert;

import query.InsertQuery;
import testutils.DatabaseConnection;
import testutils.DatabaseTestBaseClass;
import builder.query.SQLQueryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;

import static builder.statement.ConditionStatementBuilder.valueOf;
import static org.junit.Assert.assertEquals;

public class InsertTest extends DatabaseTestBaseClass {
    private SQLQueryBuilder sqlQueryBuilder;

    @Before
    public void setUp() throws SQLException {
        initializeDatabase();
        sqlQueryBuilder = new SQLQueryBuilder(DatabaseConnection.getH2DataSource());
    }

    @Test
    public void testInsertStringValue() throws SQLException {
        InsertQuery query = sqlQueryBuilder
                .insert()
                .table("person")
                .columns("firstname")
                .values()
                .setString("Miika-Lassi Kari")
                .build();

        assertEquals("INSERT INTO person (firstname) VALUES ('Miika-Lassi Kari')", query.toString());

        int result = query.execute();

        assertEquals(4, result);
    }

    @Test
    public void testInsertIntegerValue() throws SQLException {
        InsertQuery query = sqlQueryBuilder
                .insert()
                .table("person")
                .columns("id")
                .values()
                    .setInt(100)
                .build();

        assertEquals("INSERT INTO person (id) VALUES (100)", query.toString());

        int result = query.execute();

        assertEquals(100, result);
        assertThatQueryReturnsRows("SELECT * FROM person WHERE id = 100", 1);
    }

    @Test
    public void testInsertShortValue() throws SQLException {
        InsertQuery query = sqlQueryBuilder
                .insert()
                .table("all_types")
                .columns("rating")
                .values()
                .setShort((short) 4)
                .build();

        assertEquals("INSERT INTO all_types (rating) VALUES (4)", query.toString());

        int result = query.execute();

        assertEquals(14, result);
        assertThatQueryReturnsRows("SELECT * FROM all_Types WHERE rating = 4", 1);
    }

    @Test
    public void testInsertLongValue() throws SQLException {
        InsertQuery query = sqlQueryBuilder
                .insert()
                .table("all_types")
                .columns("hash")
                .values()
                    .setLong(2485394539475834568L)
                .build();

        assertEquals("INSERT INTO all_types (hash) VALUES (2485394539475834568)", query.toString());

        int result = query.execute();

        assertEquals(14, result);
        assertThatQueryReturnsRows("SELECT * FROM all_types WHERE hash = 2485394539475834568", 1);
    }

    @Test
    public void testInsertDoubleValue() throws SQLException {
        InsertQuery query = sqlQueryBuilder
                .insert()
                .table("all_types")
                .columns("age")
                .values()
                .setDouble(19.3)
                .build();

        assertEquals("INSERT INTO all_types (age) VALUES (19.3)", query.toString());

        int result = query.execute();

        assertEquals(14, result);
        assertThatQueryReturnsRows("SELECT * FROM all_types WHERE age = 19.3", 1);
    }

    @Test
    public void testInsertBigDecimalValue() throws SQLException {
        InsertQuery query = sqlQueryBuilder
                .insert()
                .table("all_types")
                .columns("price")
                .values()
                .setBigDecimal(BigDecimal.valueOf(21100.99))
                .build();

        assertEquals("INSERT INTO all_types (price) VALUES (21100.99)", query.toString());

        int result = query.execute();

        assertEquals(14, result);
        assertThatQueryReturnsRows("SELECT * FROM all_types WHERE price = 21100.99", 1);
    }

    @Test
    public void testInsertOneBooleanValue() throws SQLException {
        InsertQuery query = sqlQueryBuilder
                .insert()
                .table("all_types")
                .columns("active")
                .values()
                    .setBoolean(false)
                .build();

        assertEquals("INSERT INTO all_types (active) VALUES (false)", query.toString());

        int result = query.execute();

        assertEquals(14, result);
        assertThatQueryReturnsRows("SELECT * FROM all_types WHERE active = false", 2);
    }

    @Test
    public void testInsertOneDateValue() throws SQLException {
        InsertQuery query = sqlQueryBuilder
                .insert()
                .table("person")
                .columns("birthdate")
                .values()
                    .setDate("1978-04-03")
                .build();

        assertEquals("INSERT INTO person (birthdate) VALUES ('1978-04-03')", query.toString());

        int result = query.execute();

        assertEquals(4, result);
    }

    @Test
    public void testInsertTimeValue() throws SQLException {
        InsertQuery query = sqlQueryBuilder
                .insert()
                .table("all_types")
                .columns("clock")
                .values()
                .setTime("21:00:04")
                .build();

        assertEquals("INSERT INTO all_types (clock) VALUES ('21:00:04')", query.toString());

        int result = query.execute();

        assertEquals(14, result);
    }

    @Test
    public void testInsertTimestampValue() throws SQLException {
        InsertQuery query = sqlQueryBuilder
                .insert()
                .table("person")
                    .columns("birthdate")
                .values()
                    .setTimestamp("1978-04-03 21:00:04")
                .build();

        assertEquals("INSERT INTO person (birthdate) VALUES ('1978-04-03 21:00:04.0')", query.toString());

        int result = query.execute();

        assertEquals(4, result);
    }

    @Test
    public void testInsertByteArrayValue() throws SQLException, IOException {
        byte[] file = readFileAsByteArray("files/byte-array-test-file.txt");

        InsertQuery query = sqlQueryBuilder
                .insert()
                .table("all_types")
                .columns("contract")
                .values()
                .setByteArray(file)
                .build();

        int result = query.execute();

        assertEquals(14, result);
        assertThatQueryReturnsRows("SELECT * FROM all_types WHERE contract IS NOT NULL", 3);
    }

    @Test
    public void testInsertMultipleValues() throws SQLException {
        InsertQuery query = sqlQueryBuilder
                .insert()
                .table("person")
                    .columns("id", "birthdate", "firstname", "lastname", "age")
                .values()
                    .setInt(101)
                    .setDate("1980-04-12")
                    .setString("Miika")
                    .setString("Somero")
                    .setDouble(40.5)
                .build();

        assertEquals("INSERT INTO person (id, birthdate, firstname, lastname, age) VALUES (101, '1980-04-12', 'Miika', 'Somero', 40.5)", query.toString());

        int result = query.execute();

        assertEquals(101, result);

    }

    @Test
    public void testInsertQuery() throws SQLException {
        InsertQuery query = sqlQueryBuilder
                .insert()
                .table("person")
                .columns("id", "birthdate", "firstname", "lastname", "age")
                .sub(sqlQueryBuilder
                        .select()
                            .column("id")
                            .column("birthdate")
                            .column("firstname")
                            .column("lastname")
                            .column("age")
                        .from()
                            .table("student")
                        .where(valueOf("age").greaterThanInteger(18))
                )
                .build();

        assertEquals("INSERT INTO person (id, birthdate, firstname, lastname, age) SELECT id, birthdate, firstname, lastname, age FROM student WHERE age > 18", query.toString());

        int result = query.execute();

        assertEquals(4, result);
    }

    @Test
    public void testInsertMultipleValuesWithoutSpecifyingColumns() throws SQLException {
        InsertQuery query = sqlQueryBuilder
                .insert()
                .table("person")
                .values()
                    .setInt(102)
                    .setDate("1980-04-12")
                    .setString("Miika")
                    .setString("Somero")
                    .setInt(40)
                .build();

        assertEquals("INSERT INTO person VALUES (102, '1980-04-12', 'Miika', 'Somero', 40)", query.toString());

        int result = query.execute();

        assertEquals(102, result);
    }

    @Test
    public void testInsertQueryWithoutSpecifyingColumns() throws SQLException {
        InsertQuery query = sqlQueryBuilder
                .insert()
                .table("person")
                .sub(sqlQueryBuilder
                        .select()
                            .all()
                        .from()
                            .table("student")
                        .where(valueOf("age").lesserThanInteger(18))
                )
                .build();

        assertEquals("INSERT INTO person SELECT * FROM student WHERE age < 18", query.toString());

        int result = query.execute();

        assertEquals(6, result);
    }
}
