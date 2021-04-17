package builder.clause;

import builder.field.Field;
import factory.QueryFactory;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class FieldTest {
    private Field field;

    @Before
    public void setUp() {
        this.field = QueryFactory
                .select()
                .field("firstname");
    }

    @Test
    public void testValue() {
        String query = this.field
                .field("lastname")
                .field("age")
                .from("persons")
                .build();

        assertEquals("SELECT firstname, lastname, age FROM persons;", query);
    }

    @Test
    public void testMultipleValuesWithAliases() {
        String query = QueryFactory
                .select()
                .field("lastname").alias("last")
                .field("age")
                .field("firstname").alias("first")
                .from("persons")
                .build();

        assertEquals("SELECT lastname AS last, age, firstname AS first FROM persons;", query);
    }
}
