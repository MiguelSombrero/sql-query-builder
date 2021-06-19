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
                    .setDateTime("2020-03-03 21:00:00")
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

        assertEquals(1, result.size());



    }
}
