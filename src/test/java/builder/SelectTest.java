package builder;

import factory.QueryFactory;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class SelectTest {

    @Test
    public void testSelectFromTable() {
        String query = QueryFactory
                .select()
                    .value("lastname")
                    .value("age")
                    .value("firstname")
                .from("persons")
                .build();

        assertEquals("SELECT lastname, age, firstname FROM persons;", query);
    }

    @Test
    public void testSelectDistinctFromTable() {
        String query = QueryFactory
                .select()
                    .distinct("lastname")
                    .value("age")
                    .value("firstname")
                .from("persons")
                .build();

        assertEquals("SELECT DISTINCT lastname, age, firstname FROM persons;", query);
    }
}
