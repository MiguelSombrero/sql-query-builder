package builder.statement.select.column;

import database.Row;
import testutils.DatabaseConnection;
import testutils.DatabaseTestBaseClass;
import factory.SelectQueryFactory;
import org.junit.Before;
import org.junit.Test;
import query.SelectQuery;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ColumnTest extends DatabaseTestBaseClass {
    private SelectQueryFactory selectQueryFactory;

    @Before
    public void setUp() throws SQLException {
        initializeDatabase();
        selectQueryFactory = new SelectQueryFactory(DatabaseConnection.getDataSource());
    }

    @Test
    public void testSelect() throws SQLException {
        SelectQuery query = selectQueryFactory
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
        SelectQuery query = selectQueryFactory
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
        SelectQuery query = selectQueryFactory
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
    public void testSelectCount() throws SQLException {
        SelectQuery query = selectQueryFactory
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
    public void testSelectMin() throws SQLException {
        SelectQuery query = selectQueryFactory
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
        SelectQuery query = selectQueryFactory
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
        SelectQuery query = selectQueryFactory
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
        SelectQuery query = selectQueryFactory
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
        SelectQuery query = selectQueryFactory
                .select()
                    .min("age")
                    .max("birthdate")
                    .count("*")
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
        SelectQuery query = selectQueryFactory
                .select()
                    .column("lastname").alias("last")
                    .min("age").alias("minAge")
                    .column("firstname").alias("first")
                    .count("*").alias("count")
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
    public void testSelectFisrtColumnWithSQLInjection() {
        selectQueryFactory
                .select()
                .column(";DROP")
                .from()
                    .table("person")
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSelectColumnWithSQLInjection() {
        selectQueryFactory
                .select()
                    .column("firstname")
                    .column(";DROP")
                .from()
                .table("person")
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSelectColumnAliasWithSQLInjection() {
        selectQueryFactory
                .select()
                .column("firstname").alias(";DROP")
                .from()
                .table("person")
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSelectAggregateFunctionWithSQLInjection() {
        selectQueryFactory
                .select()
                    .count(";DROP")
                .from()
                    .table("person")
                .build();
    }
}
