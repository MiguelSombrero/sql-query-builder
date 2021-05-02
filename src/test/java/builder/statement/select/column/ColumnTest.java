package builder.statement.select.column;

import database.DatabaseTestBaseClass;
import factory.QueryFactory;
import org.junit.Test;

import java.sql.SQLException;

import static junit.framework.Assert.assertEquals;

public class ColumnTest extends DatabaseTestBaseClass {

    @Test
    public void testSelect() throws SQLException {
        String query = QueryFactory
                .select()
                    .column("lastname")
                    .column("age")
                .from()
                    .table("person")
                .build();

        assertEquals("SELECT lastname, age FROM person;", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testSelectTop() throws SQLException {
        String query = QueryFactory
                .selectTop(100)
                    .column("lastname")
                    .column("age")
                    .column("firstname")
                .from()
                    .table("person")
                .build();

        assertEquals("SELECT TOP 100 lastname, age, firstname FROM person;", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testSelectDistinct() throws SQLException {
        String query = QueryFactory
                .selectDistinct()
                    .column("lastname")
                    .column("age")
                    .column("firstname")
                .from()
                    .table("person")
                .build();

        assertEquals("SELECT DISTINCT lastname, age, firstname FROM person;", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testSelectCount() throws SQLException {
        String query = QueryFactory
                .select()
                    .count("age")
                .from()
                    .table("person")
                .build();

        assertEquals("SELECT COUNT(age) FROM person;", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testSelectMin() throws SQLException {
        String query = QueryFactory
                .select()
                    .min("age")
                .from()
                    .table("person")
                .build();

        assertEquals("SELECT MIN(age) FROM person;", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testSelectMax() throws SQLException {
        String query = QueryFactory
                .select()
                    .max("age")
                .from()
                    .table("person")
                .build();

        assertEquals("SELECT MAX(age) FROM person;", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testSelectAvg() throws SQLException {
        String query = QueryFactory
                .select()
                    .avg("age")
                .from()
                    .table("person")
                .build();

        assertEquals("SELECT AVG(age) FROM person;", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testSelectSum() throws SQLException {
        String query = QueryFactory
                .select()
                    .sum("age")
                .from()
                    .table("person")
                .build();

        assertEquals("SELECT SUM(age) FROM person;", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testMultipleColumnsWithAliases() throws SQLException {
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

        assertEquals("SELECT lastname AS last, MIN(age) AS minAge, firstname AS first, COUNT(*) AS count FROM person GROUP BY firstname;", query);
        assertThatQueryIsValidSQL(query);
    }
}
