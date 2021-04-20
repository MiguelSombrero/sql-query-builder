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
    public void testInnerJoin() throws SQLException {
        String query = this.field
                .from("person")
                .innerJoin("address").on("person.id = address.person_id")
                .build();

        assertEquals("SELECT * FROM person INNER JOIN address ON person.id = address.person_id;", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testLeftJoin() throws SQLException {
        String query = this.field
                .from("person")
                .leftJoin("address").on("person.id = address.person_id")
                .build();

        assertEquals("SELECT * FROM person LEFT JOIN address ON person.id = address.person_id;", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void rightJoin() throws SQLException {
        String query = this.field
                .from("person")
                .rightJoin("address").on("person.id = address.person_id")
                .build();

        assertEquals("SELECT * FROM person RIGHT JOIN address ON person.id = address.person_id;", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testMultipleJoins() throws SQLException {
        String query = this.field
                .from("person")
                .leftJoin("address").on("person.id = address.person_id")
                .innerJoin("course").on("person.id = course.person_id")
                .rightJoin("school").on("course.school_id = school.id")
                .build();

        assertEquals("SELECT * FROM person LEFT JOIN address ON person.id = address.person_id INNER JOIN course ON person.id = course.person_id RIGHT JOIN school ON course.school_id = school.id;", query);
        assertThatQueryIsValidSQL(query);
    }
}
