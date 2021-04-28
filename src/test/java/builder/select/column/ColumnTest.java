package builder.select.column;

import database.DatabaseTestBaseClass;
import factory.QueryFactory;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static junit.framework.Assert.assertEquals;

public class ColumnTest extends DatabaseTestBaseClass {
    private Column column;

    @Before
    public void setUpQuery() {
        this.column = QueryFactory
                .select()
                .field("firstname");
    }

    @Test
    public void testField() throws SQLException {
        String query = this.column
                .field("lastname")
                .field("age")
                .from("person")
                .build();

        assertEquals("SELECT firstname, lastname, age FROM person;", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testMultipleFieldsWithAliases() throws SQLException {
        String query = QueryFactory
                .select()
                .field("lastname").alias("last")
                .field("age")
                .field("firstname").alias("first")
                .from("person")
                .build();

        assertEquals("SELECT lastname AS last, age, firstname AS first FROM person;", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testSelectMin() throws SQLException {
        String query = QueryFactory
                .select()
                .field("firstname")
                .min("age")
                .from("person")
                .groupBy().column("firstname")
                .build();

        assertEquals("SELECT firstname, MIN(age) FROM person GROUP BY firstname;", query);
        assertThatQueryIsValidSQL(query);
    }
}
