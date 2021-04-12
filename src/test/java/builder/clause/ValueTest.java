package builder.clause;

import factory.QueryFactory;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class ValueTest {
    private Value value;

    @Before
    public void setUp() {
        this.value = QueryFactory
                .select()
                .value("firstname");
    }

    @Test
    public void testValue() {
        String query = this.value
                .value("lastname")
                .value("age")
                .from("persons")
                .build();

        assertEquals("SELECT firstname, lastname, age FROM persons;", query);
    }

    @Test
    public void testMultipleValuesWithAliases() {
        String query = QueryFactory
                .select()
                .value("lastname").alias("last")
                .value("age")
                .value("firstname").alias("first")
                .from("persons")
                .build();

        assertEquals("SELECT lastname AS last, age, firstname AS first FROM persons;", query);
    }
}
