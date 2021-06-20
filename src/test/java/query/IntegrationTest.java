package query;

import builder.query.SQLQueryBuilder;
import builder.query.create.table.column.DataType;
import database.row.Row;
import org.junit.Before;
import org.junit.Test;
import testutils.DatabaseConnection;
import testutils.DatabaseTestBaseClass;

import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.List;

import static builder.clause.ConditionClauseBuilder.valueOf;
import static org.junit.Assert.assertEquals;

public class IntegrationTest extends DatabaseTestBaseClass {
    private SQLQueryBuilder sqlQueryBuilder;

    @Before
    public void setUp() throws SQLException {
        initializeDatabase();
        sqlQueryBuilder = new SQLQueryBuilder(DatabaseConnection.getDataSource());
    }

    @Test
    public void testAllQueryTypes() throws SQLException {
        CreateQuery create = sqlQueryBuilder.create()
                .table("cars")
                .column("ID").type(DataType.INT).primaryKey()
                .column("hash").type(DataType.BIGINT)
                .column("age").type(DataType.DOUBLE)
                .column("date").type(DataType.DATE)
                .column("datetime").type(DataType.DATETIME)
                .column("created").type(DataType.TIMESTAMP)
                .column("active").type(DataType.BOOLEAN)
                .column("country").type(DataType.CHAR)
                .column("model").type(DataType.VARCHAR_32)
                .column("brand").type(DataType.VARCHAR_64)
                .column("disclaimer").type(DataType.VARCHAR_128)
                .column("description").type(DataType.VARCHAR_255)
                .column("contract").type(DataType.BLOB)
                .build();

        create.execute();

        assertThatQueryReturnsRows("SELECT * FROM cars", 0);

        InsertQuery insert = sqlQueryBuilder.insert()
                .table("cars")
                .values()
                    .setInt(1)
                    .setLong(123456789)
                    .setDouble(3.4)
                    .setDate("2020-02-02")
                    .setDateTime("2021-01-01 10:01:01")
                    .setDateTime("2020-03-03 21:00:02")
                    .setBoolean(true)
                    .setString("10")
                    .setString("Taunus")
                    .setString("Ford")
                    .setString("Might break")
                    .setString("Good car")
                    .setByteArray("some file".getBytes(StandardCharsets.UTF_8))
                    .build();

        insert.execute();

        SelectQuery select = sqlQueryBuilder.select().all()
                .from().table("cars")
                .build();

        List<Row> result = select.execute();

        Row firstRow = result.get(0);

        assertEquals(1, result.size());
        assertEquals(1, firstRow.getInteger("id"));
        assertEquals(123456789, firstRow.getLong("hash"));
        assertEquals(3.4, firstRow.getDouble("age"), 0.1);
        assertEquals("2020-02-02", firstRow.getLocalDate("date").toString());
        assertEquals("2021-01-01T10:01:01", firstRow.getLocalDateTime("datetime").toString());
        assertEquals("2020-03-03T21:00:02", firstRow.getLocalDateTime("created").toString());
        assertEquals(true, firstRow.getBoolean("active"));
        assertEquals(String.valueOf(10), firstRow.getString("country"));
        assertEquals("Taunus", firstRow.getString("model"));
        assertEquals("Ford", firstRow.getString("brand"));
        assertEquals("Might break", firstRow.getString("disclaimer"));
        assertEquals("Good car", firstRow.getString("description"));

        UpdateQuery update = sqlQueryBuilder
                .update()
                .table("cars")
                .column("age")
                .setDouble(5.4)
                .where(valueOf("id").equalsInteger(1))
                .build();

        update.execute();

        SelectQuery selectAgain = sqlQueryBuilder
                .select().all()
                .from().table("cars")
                .build();

        List<Row> secondResult = selectAgain.execute();

        Row firstRowAgain = secondResult.get(0);
        assertEquals(5.4, firstRowAgain.getDouble("age"), 0.1);


    }
}
