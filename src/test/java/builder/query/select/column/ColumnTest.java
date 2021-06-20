package builder.query.select.column;

import builder.query.SQLQueryBuilder;
import database.row.Row;
import query.SelectQuery;
import testutils.DatabaseConnection;
import testutils.DatabaseTestBaseClass;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ColumnTest extends DatabaseTestBaseClass {
    private SQLQueryBuilder sqlQueryBuilder;

    @Before
    public void setUp() throws SQLException {
        initializeDatabase();
        sqlQueryBuilder = new SQLQueryBuilder(DatabaseConnection.getDataSource());
    }

    @Test
    public void testSelect() throws SQLException {
        SelectQuery query = sqlQueryBuilder
                .select()
                    .column("lastname")
                    .column("age")
                .from()
                    .table("person")
                .build();

        assertEquals("SELECT lastname, age FROM person", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 3);
        assertColumnCount(result, 2);
    }

    @Test
    public void testSelectTop() throws SQLException {
        SelectQuery query = sqlQueryBuilder
                .selectTop(2)
                    .column("lastname")
                    .column("age")
                    .column("firstname")
                .from()
                    .table("person")
                .build();

        assertEquals("SELECT TOP 2 lastname, age, firstname FROM person", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 2);
        assertColumnCount(result, 3);
    }

    @Test
    public void testSelectDistinct() throws SQLException {
        SelectQuery query = sqlQueryBuilder
                .selectDistinct()
                    .column("lastname")
                    .column("age")
                    .column("firstname")
                .from()
                    .table("person")
                .build();

        assertEquals("SELECT DISTINCT lastname, age, firstname FROM person", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 3);
        assertColumnCount(result, 3);
    }

    @Test
    public void testSelectAll() throws SQLException {
        SelectQuery query = sqlQueryBuilder
                .select()
                    .all()
                .from()
                    .table("person")
                .build();

        assertEquals("SELECT * FROM person", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 3);
        assertColumnCount(result, 5);
    }

    @Test
    public void testSelectCount() throws SQLException {
        SelectQuery query = sqlQueryBuilder
                .select()
                    .count("age")
                .from()
                    .table("person")
                .build();

        assertEquals("SELECT COUNT(age) FROM person", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 1);
        assertColumnCount(result, 1);
    }

    @Test
    public void testSelectCountAll() throws SQLException {
        SelectQuery query = sqlQueryBuilder
                .select()
                    .countAll()
                .from()
                    .table("person")
                .build();

        assertEquals("SELECT COUNT(*) FROM person", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 1);
        assertColumnCount(result, 1);
    }

    @Test
    public void testSelectMin() throws SQLException {
        SelectQuery query = sqlQueryBuilder
                .select()
                    .min("age")
                .from()
                    .table("person")
                .build();

        assertEquals("SELECT MIN(age) FROM person", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 1);
        assertColumnCount(result, 1);
    }

    @Test
    public void testSelectMax() throws SQLException {
        SelectQuery query = sqlQueryBuilder
                .select()
                    .max("age")
                .from()
                    .table("person")
                .build();

        assertEquals("SELECT MAX(age) FROM person", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 1);
        assertColumnCount(result, 1);
    }

    @Test
    public void testSelectAvg() throws SQLException {
        SelectQuery query = sqlQueryBuilder
                .select()
                    .avg("age")
                .from()
                    .table("person")
                .build();

        assertEquals("SELECT AVG(age) FROM person", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 1);
        assertColumnCount(result, 1);
    }

    @Test
    public void testSelectSum() throws SQLException {
        SelectQuery query = sqlQueryBuilder
                .select()
                    .sum("age")
                .from()
                    .table("person")
                .build();

        assertEquals("SELECT SUM(age) FROM person", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 1);
        assertColumnCount(result, 1);
    }

    @Test
    public void testMultipleAggregateFunctions() throws SQLException {
        SelectQuery query = sqlQueryBuilder
                .select()
                    .min("age")
                    .max("birthdate")
                    .countAll()
                .from()
                    .table("person")
                .groupBy()
                    .column("firstname")
                .build();

        assertEquals("SELECT MIN(age), MAX(birthdate), COUNT(*) FROM person GROUP BY firstname", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 3);
        assertColumnCount(result, 3);
    }

    @Test
    public void testMultipleColumnsWithAliases() throws SQLException {
        SelectQuery query = sqlQueryBuilder
                .select()
                    .column("lastname").alias("last")
                    .min("age").alias("minAge")
                    .column("firstname").alias("first")
                    .countAll().alias("count")
                .from()
                    .table("person")
                .groupBy()
                    .column("firstname")
                .build();

        assertEquals("SELECT lastname AS last, MIN(age) AS minAge, firstname AS first, COUNT(*) AS count FROM person GROUP BY firstname", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 3);
        assertColumnCount(result, 4);
    }
}
