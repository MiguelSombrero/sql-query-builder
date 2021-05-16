package builder.statement.select.column;

import database.DatabaseTestBaseClass;
import factory.QueryFactory;
import org.junit.Before;
import org.junit.Test;

import javax.xml.bind.ValidationException;
import java.sql.SQLException;

import static junit.framework.Assert.assertEquals;

public class ColumnTest extends DatabaseTestBaseClass {

    @Before
    public void setUp() {
        initializeDatabase();
    }

    @Test
    public void testSelect() throws SQLException, ValidationException {
        String query = QueryFactory
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
    public void testSelectTop() throws SQLException, ValidationException {
        String query = QueryFactory
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
    public void testSelectDistinct() throws SQLException, ValidationException {
        String query = QueryFactory
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
    public void testSelectCount() throws SQLException, ValidationException {
        String query = QueryFactory
                .select()
                    .count("age")
                .from()
                    .table("person")
                .build();

        assertEquals("SELECT COUNT(age) FROM person", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testSelectMin() throws SQLException, ValidationException {
        String query = QueryFactory
                .select()
                    .min("age")
                .from()
                    .table("person")
                .build();

        assertEquals("SELECT MIN(age) FROM person", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testSelectMax() throws SQLException, ValidationException {
        String query = QueryFactory
                .select()
                    .max("age")
                .from()
                    .table("person")
                .build();

        assertEquals("SELECT MAX(age) FROM person", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testSelectAvg() throws SQLException, ValidationException {
        String query = QueryFactory
                .select()
                    .avg("age")
                .from()
                    .table("person")
                .build();

        assertEquals("SELECT AVG(age) FROM person", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testSelectSum() throws SQLException, ValidationException {
        String query = QueryFactory
                .select()
                    .sum("age")
                .from()
                    .table("person")
                .build();

        assertEquals("SELECT SUM(age) FROM person", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testMultipleAggregateFunctions() throws SQLException, ValidationException {
        String query = QueryFactory
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
    public void testMultipleColumnsWithAliases() throws SQLException, ValidationException {
        String query = QueryFactory
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

    @Test(expected = ValidationException.class)
    public void testSelectColumnWithSQLInjection() throws ValidationException {
        QueryFactory
                .select()
                .column(";DROP")
                .from()
                    .table("person")
                .build();
    }

    @Test(expected = ValidationException.class)
    public void testSelectColumnAliasWithSQLInjection() throws ValidationException {
        QueryFactory
                .select()
                .column("firstname").alias(";DROP")
                .from()
                .table("person")
                .build();
    }

    @Test(expected = ValidationException.class)
    public void testSelectAggregateFunctionWithSQLInjection() throws ValidationException {
        QueryFactory
                .select()
                    .count(";DROP")
                .from()
                    .table("person")
                .build();
    }
}
