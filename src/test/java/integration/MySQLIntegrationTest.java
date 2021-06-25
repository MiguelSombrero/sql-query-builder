package integration;

import builder.query.SQLQueryBuilder;
import com.mysql.cj.jdbc.MysqlDataSource;
import database.row.Row;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import query.*;

import java.math.BigDecimal;
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
                .table()
                .ifExists()
                .name("cars")
                .build();

        drop.execute();
    }

    @Test
    public void testAllQueryTypes() throws SQLException {
        CreateQuery create = sqlQueryBuilder.create()
                .table("cars")
                .column("ID").type("INT").primaryKey()
                //.column("rating").type("SMALLINT")
                .column("hash").type("BIGINT")
                .column("age").type("DOUBLE")
                .column("price").type("DECIMAL(8,2)")
                .column("taxes").type("NUMERIC(5,2)")
                .column("date").type("DATE")
                .column("created").type("TIMESTAMP")
                .column("active").type("BOOLEAN")
                .column("country").type("CHAR(32)")
                .column("model").type("VARCHAR(32)")
                .column("brand").type("VARCHAR(64)")
                .column("description").type("VARCHAR(255)")
                .column("contract").type("BLOB")
                .build();

        create.execute();

        InsertQuery insert = sqlQueryBuilder.insert()
                .table("cars")
                .values()
                    .setInt(1)
                    //.setShort(Short.valueOf("7"))
                    .setLong(123456789)
                    .setDouble(3.4)
                    .setBigDecimal(BigDecimal.valueOf(123456.78))
                    .setBigDecimal(BigDecimal.valueOf(11.25))
                    .setDate("2020-02-02")
                    .setTimestamp("2020-03-03 21:00:02.123")
                    .setBoolean(true)
                    .setString("USA")
                    .setString("Taunus")
                    .setString("Ford")
                    .setString("Might break")
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
        //assertEquals(7, firstRow.getShort("rating"));
        assertEquals(123456789, firstRow.getLong("hash"));
        assertEquals(3.4, firstRow.getDouble("age"), 0.1);
        assertEquals(BigDecimal.valueOf(123456.78), firstRow.getBigDecimal("price"));
        assertEquals(BigDecimal.valueOf(11.25), firstRow.getBigDecimal("taxes"));
        assertEquals("2020-02-02", firstRow.getDate("date").toString());
        assertEquals("2020-03-03 21:00:02.123", firstRow.getTimestamp("created").toString());
        assertEquals(true, firstRow.getBoolean("active"));
        assertEquals("USA", firstRow.getString("country"));
        assertEquals("Taunus", firstRow.getString("model"));
        assertEquals("Ford", firstRow.getString("brand"));
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
