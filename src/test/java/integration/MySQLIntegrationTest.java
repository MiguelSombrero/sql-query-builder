package integration;

import builder.query.SQLQueryBuilder;
import builder.query.create.table.column.DataType;
import com.mysql.cj.jdbc.MysqlDataSource;
import database.row.Row;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import query.*;

import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.List;

import static builder.clause.ConditionClauseBuilder.valueOf;
import static org.junit.Assert.assertEquals;

@Ignore("These integration tests is run against live MySQL database container and must be executed manually")
public class MySQLIntegrationTest {
    private SQLQueryBuilder sqlQueryBuilder;

    @Before
    public void setUp() throws SQLException {
        // Before tests start MySQL container:
        // docker run --name mysql_db -p 3306:3306 -e MYSQL_ROOT_PASSWORD=sa -e MYSQL_DATABASE=test_db -d mysql:latest

        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setURL("jdbc:mysql://localhost:3306/test_db");
        dataSource.setUser("root");
        dataSource.setPassword("sa");

        sqlQueryBuilder = new SQLQueryBuilder(dataSource);

        DropQuery drop = sqlQueryBuilder
                .drop()
                .table("cars")
                .build();

        drop.execute();
    }

    @Test
    public void testAllQueryTypes() throws SQLException {
        CreateQuery create = sqlQueryBuilder.create()
                .table("cars")
                .column("ID").type(DataType.INT).primaryKey()
                .column("hash").type(DataType.BIGINT)
                .column("age").type(DataType.DOUBLE)
                .column("date").type(DataType.DATE)
                .column("created").type(DataType.TIMESTAMP)
                .column("active").type(DataType.BOOLEAN)
                .column("country").type(DataType.CHAR_32)
                .column("model").type(DataType.VARCHAR_32)
                .column("brand").type(DataType.VARCHAR_64)
                .column("disclaimer").type(DataType.VARCHAR_128)
                .column("description").type(DataType.VARCHAR_255)
                .column("contract").type(DataType.BLOB)
                .build();

        create.execute();

        InsertQuery insert = sqlQueryBuilder.insert()
                .table("cars")
                .values()
                    .setInt(1)
                    .setLong(123456789)
                    .setDouble(3.4)
                    .setDate("2020-02-02")
                    .setTimestamp("2020-03-03 21:00:02.0")
                    .setBoolean(true)
                    .setString("USA")
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
        assertEquals("2020-02-02", firstRow.getDate("date").toString());
        assertEquals("2020-03-03 21:00:02.0", firstRow.getTimestamp("created").toString());
        assertEquals(true, firstRow.getBoolean("active"));
        assertEquals("USA", firstRow.getString("country"));
        assertEquals("Taunus", firstRow.getString("model"));
        assertEquals("Ford", firstRow.getString("brand"));
        assertEquals("Might break", firstRow.getString("disclaimer"));
        assertEquals("Good car", firstRow.getString("description"));
        assertEquals("some file", new String(firstRow.getBytes("contract"), StandardCharsets.UTF_8));

        UpdateQuery update = sqlQueryBuilder
                .update()
                .table("cars")
                .column("age")
                .setDouble(5.4)
                .where(valueOf("id").equalsInteger(1))
                .build();

        update.execute();

        List<Row> secondResult = select.execute();

        Row firstRowAgain = secondResult.get(0);
        assertEquals(5.4, firstRowAgain.getDouble("age"), 0.1);

        DeleteQuery delete = sqlQueryBuilder
                .delete()
                .table("cars")
                .where(valueOf("id").equalsInteger(1))
                .build();

        delete.execute();

        List<Row> thirdResult = select.execute();
        assertEquals(0, thirdResult.size());
    }
}
