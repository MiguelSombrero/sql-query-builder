package builder.statement.select.column;

import query.dql.Row;
import query.dql.SelectQuery;
import testutils.DatabaseConnection;
import testutils.DatabaseTestBaseClass;
import query.QueryFactory;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ColumnTest extends DatabaseTestBaseClass {
    private QueryFactory queryFactory;

    @Before
    public void setUp() throws SQLException {
        initializeDatabase();
        queryFactory = new QueryFactory(DatabaseConnection.getDataSource());
    }

    @Test
    public void testSelect() throws SQLException {
        SelectQuery query = queryFactory
                .select()
                    .column("lastname")
                    .column("age")
                .from()
                    .table("person")
                .build();

        assertEquals("SELECT lastname, age FROM person", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 3);
        assertRowLength(result, 2);
    }

    @Test
    public void testSelectTop() throws SQLException {
        SelectQuery query = queryFactory
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
        assertRowLength(result, 3);
    }

    @Test
    public void testSelectDistinct() throws SQLException {
        SelectQuery query = queryFactory
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
        assertRowLength(result, 3);
    }

    @Test
    public void testSelectAll() throws SQLException {
        SelectQuery query = queryFactory
                .select()
                    .all()
                .from()
                    .table("person")
                .build();

        assertEquals("SELECT * FROM person", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 3);
        assertRowLength(result, 5);
    }

    @Test
    public void testSelectCount() throws SQLException {
        SelectQuery query = queryFactory
                .select()
                    .count("age")
                .from()
                    .table("person")
                .build();

        assertEquals("SELECT COUNT(age) FROM person", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 1);
        assertRowLength(result, 1);
    }

    @Test
    public void testSelectCountAll() throws SQLException {
        SelectQuery query = queryFactory
                .select()
                    .countAll()
                .from()
                    .table("person")
                .build();

        assertEquals("SELECT COUNT(*) FROM person", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 1);
        assertRowLength(result, 1);
    }

    @Test
    public void testSelectMin() throws SQLException {
        SelectQuery query = queryFactory
                .select()
                    .min("age")
                .from()
                    .table("person")
                .build();

        assertEquals("SELECT MIN(age) FROM person", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 1);
        assertRowLength(result, 1);
    }

    @Test
    public void testSelectMax() throws SQLException {
        SelectQuery query = queryFactory
                .select()
                    .max("age")
                .from()
                    .table("person")
                .build();

        assertEquals("SELECT MAX(age) FROM person", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 1);
        assertRowLength(result, 1);
    }

    @Test
    public void testSelectAvg() throws SQLException {
        SelectQuery query = queryFactory
                .select()
                    .avg("age")
                .from()
                    .table("person")
                .build();

        assertEquals("SELECT AVG(age) FROM person", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 1);
        assertRowLength(result, 1);
    }

    @Test
    public void testSelectSum() throws SQLException {
        SelectQuery query = queryFactory
                .select()
                    .sum("age")
                .from()
                    .table("person")
                .build();

        assertEquals("SELECT SUM(age) FROM person", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 1);
        assertRowLength(result, 1);
    }

    @Test
    public void testMultipleAggregateFunctions() throws SQLException {
        SelectQuery query = queryFactory
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
        assertRowLength(result, 3);
    }

    @Test
    public void testMultipleColumnsWithAliases() throws SQLException {
        SelectQuery query = queryFactory
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
        assertRowLength(result, 4);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSelectColumnWithParameter() {
        queryFactory
                .select()
                    .column("*")
                .from()
                    .table("person")
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSelectFirstColumnWithSQLInjection() {
        queryFactory
                .select()
                .column(";DROP")
                .from()
                    .table("person")
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSelectColumnWithSQLInjection() {
        queryFactory
                .select()
                    .column("firstname")
                    .column(";DROP")
                .from()
                .table("person")
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSelectColumnAliasWithSQLInjection() {
        queryFactory
                .select()
                .column("firstname").alias(";DROP")
                .from()
                .table("person")
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSelectAggregateFunctionWithSQLInjection() {
        queryFactory
                .select()
                    .count(";DROP")
                .from()
                    .table("person")
                .build();
    }
}
