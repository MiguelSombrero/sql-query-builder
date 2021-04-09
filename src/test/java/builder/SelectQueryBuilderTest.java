package builder;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class SelectQueryBuilderTest {
    SelectQueryBuilder builder;

    @Before
    public void setUp() {
        this.builder = QueryFactory.select();
    }

    @Test
    public void testSelectAll() {
        String query = builder.value("*").build();
        assertEquals("SELECT *", query);
    }

    @Test
    public void testSelectMultipleValues() {
        String query = builder
                .value("lastname")
                .value("age")
                .value("firstname")
                .build();

        assertEquals("SELECT lastname, age, firstname", query);
    }

    @Test
    public void testSelectMultipleValuesWithOneAlias() {
        String query = builder
                .value("lastname")
                .value("age")
                .value("firstname").as("name")
                .build();

        assertEquals("SELECT lastname, age, firstname AS name", query);
    }

    @Test
    public void testSelectMultipleValuesFrom() {
        String query = builder
                .value("lastname")
                .value("age")
                .value("firstname")
                .from("persons")
                .build();

        assertEquals("SELECT lastname, age, firstname FROM persons", query);
    }
}
