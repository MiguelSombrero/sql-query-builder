package builder;

import factory.QueryFactory;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class FirstFieldTest {

    @Test
    public void testSelectFromTable() {
        String query = QueryFactory
                .select()
                    .field("lastname")
                    .field("age")
                    .field("firstname")
                .from("persons")
                .build();

        assertEquals("SELECT lastname, age, firstname FROM persons;", query);
    }

    @Test
    public void testSelectDistinctFromTable() {
        String query = QueryFactory
                .selectDistinct()
                    .field("lastname")
                    .field("age")
                    .field("firstname")
                .from("persons")
                .build();

        assertEquals("SELECT DISTINCT lastname, age, firstname FROM persons;", query);
    }
}
