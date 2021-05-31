package builder.statement.select.column;

import database.DatabaseConnection;
import database.DatabaseTestBaseClass;
import factory.SelectQueryFactory;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

public class ColumnTest extends DatabaseTestBaseClass {
    private SelectQueryFactory selectQueryFactory;

    @Before
    public void setUp() throws SQLException {
        initializeDatabase();
        selectQueryFactory = new SelectQueryFactory(DatabaseConnection.getConnection());
    }

    @Test
    public void testSelect() throws SQLException {
        String query = selectQueryFactory
                .select()
                    .column("lastname")
                    .column("age")
                .from()
                    .table("person")
                .build();

        assertEquals("SELECT lastname, age FROM person", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testSelectTop() throws SQLException {
        String query = selectQueryFactory
                .selectTop(100)
                    .column("lastname")
                    .column("age")
                    .column("firstname")
                .from()
                    .table("person")
                .build();

        assertEquals("SELECT TOP 100 lastname, age, firstname FROM person", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testSelectDistinct() throws SQLException {
        String query = selectQueryFactory
                .selectDistinct()
                    .column("lastname")
                    .column("age")
                    .column("firstname")
                .from()
                    .table("person")
                .build();

        assertEquals("SELECT DISTINCT lastname, age, firstname FROM person", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testSelectCount() throws SQLException {
        String query = selectQueryFactory
                .select()
                    .count("age")
                .from()
                    .table("person")
                .build();

        assertEquals("SELECT COUNT(age) FROM person", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testSelectMin() throws SQLException {
        String query = selectQueryFactory
                .select()
                    .min("age")
                .from()
                    .table("person")
                .build();

        assertEquals("SELECT MIN(age) FROM person", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testSelectMax() throws SQLException {
        String query = selectQueryFactory
                .select()
                    .max("age")
                .from()
                    .table("person")
                .build();

        assertEquals("SELECT MAX(age) FROM person", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testSelectAvg() throws SQLException {
        String query = selectQueryFactory
                .select()
                    .avg("age")
                .from()
                    .table("person")
                .build();

        assertEquals("SELECT AVG(age) FROM person", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testSelectSum() throws SQLException {
        String query = selectQueryFactory
                .select()
                    .sum("age")
                .from()
                    .table("person")
                .build();

        assertEquals("SELECT SUM(age) FROM person", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testMultipleAggregateFunctions() throws SQLException {
        String query = selectQueryFactory
                .select()
                    .min("age")
                    .max("birthdate")
                    .count("*")
                .from()
                    .table("person")
                .groupBy()
                    .column("firstname")
                .build();

        assertEquals("SELECT MIN(age), MAX(birthdate), COUNT(*) FROM person GROUP BY firstname", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testMultipleColumnsWithAliases() throws SQLException {
        String query = selectQueryFactory
                .select()
                    .column("lastname").alias("last")
                    .min("age").alias("minAge")
                    .column("firstname").alias("first")
                    .count("*").alias("count")
                .from()
                    .table("person")
                .groupBy().column("firstname")
                .build();

        assertEquals("SELECT lastname AS last, MIN(age) AS minAge, firstname AS first, COUNT(*) AS count FROM person GROUP BY firstname", query);
        assertThatQueryIsValidSQL(query);
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
