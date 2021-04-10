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

    @Test
    public void testSelectMultipleValuesWithAliasesFromTable() {
        String query = QueryFactory
                .select()
                    .value("lastname").as("last")
                    .value("age")
                    .value("firstname").as("first")
                .from("persons")
                .build();

        assertEquals("SELECT lastname AS last, age, firstname AS first FROM persons;", query);
    }

    @Test
    public void testSelectFromTableWithAlias() {
        String query = QueryFactory
                .select()
                    .value("lastname")
                    .value("age")
                    .value("firstname")
                .from("persons").alias("p")
                .build();

        assertEquals("SELECT lastname, age, firstname FROM persons p;", query);
    }
}
