package builder.field;

import database.DatabaseTestBaseClass;
import factory.QueryFactory;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static junit.framework.Assert.assertEquals;

public class FieldTest extends DatabaseTestBaseClass {
    private Field field;

    @Before
    public void setUpQuery() {
        this.field = QueryFactory
                .select()
                .field("firstname");
    }

    @Test
    public void testField() throws SQLException {
        String query = this.field
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
}
