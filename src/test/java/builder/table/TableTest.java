package builder.table;

import builder.field.Field;
import database.DatabaseTestBaseClass;
import factory.QueryFactory;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static junit.framework.Assert.assertEquals;

public class TableTest extends DatabaseTestBaseClass {
    private Field field;

    @Before
    public void setUpQuery() {
        this.field = QueryFactory
                .select()
                .field("*");
    }

    @Test
    public void testFromOneTable() throws SQLException {
        String query = this.field
                .from("person")
                .build();

        assertEquals("SELECT * FROM person;", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testFromMultipleTables() throws SQLException {
        String query = this.field
                .from("person")
                .and("address")
                .and("course")
                .build();

        assertEquals("SELECT * FROM person, address, course;", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testFromMultipleTablesWithAliases() throws SQLException {
        String query = this.field
                .from("person").alias("p")
                .and("address").alias("a")
                .and("course").alias("h")
                .build();

        assertEquals("SELECT * FROM person AS p, address AS a, course AS h;", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testInnerJoin() {
        String query = this.field
                .from("persons")
                .innerJoin("addresses").on("persons.id = addresses.person_id")
                .build();

        assertEquals("SELECT * FROM persons INNER JOIN addresses ON persons.id = addresses.person_id;", query);
    }

    @Test
    public void testLeftJoin() {
        String query = this.field
                .from("persons")
                .leftJoin("addresses").on("persons.id = addresses.person_id")
                .build();

        assertEquals("SELECT * FROM persons LEFT JOIN addresses ON persons.id = addresses.person_id;", query);
    }

    @Test
    public void testMultipleJoins() {
        String query = this.field
                .from("persons")
                .leftJoin("addresses").on("persons.id = addresses.person_id")
                .innerJoin("accounts").on("persons.id = accounts.person_id")
                .build();

        assertEquals("SELECT * FROM persons LEFT JOIN addresses ON persons.id = addresses.person_id INNER JOIN accounts ON persons.id = accounts.person_id;", query);
    }
}
