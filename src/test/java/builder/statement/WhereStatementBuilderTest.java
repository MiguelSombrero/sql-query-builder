package builder.statement;

import builder.QueryFactory;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class WhereStatementBuilderTest {

    @Test
    public void testSelectFromWhereStringEquals() {
        String query = QueryFactory
                .select()
                    .value("lastname")
                    .value("firstname")
                .from("persons")
                .where("firstname").equals("Miika")
                .build();

        assertEquals("SELECT lastname, firstname FROM persons WHERE firstname = 'Miika';", query);
    }

    @Test
    public void testSelectFromWhereIntegerEquals() {
        String query = QueryFactory
                .select()
                .value("lastname")
                .value("firstname")
                .from("persons")
                .where("age").equals(18)
                .build();

        assertEquals("SELECT lastname, firstname FROM persons WHERE age = 18;", query);
    }

    @Test
    public void testSelectFromWhereDateTimeIsGreaterThan() {
        String query = QueryFactory
                .select()
                .value("lastname")
                .value("firstname")
                .from("persons")
                .where("birthdate").isGreaterThan("2020-02-28T21:00:00.000")
                .build();

        assertEquals("SELECT lastname, firstname FROM persons WHERE birthdate > '2020-02-28T21:00:00.000';", query);
    }

    @Test
    public void testSelectFromWhereIntegerIsGreaterThan() {
        String query = QueryFactory
                .select()
                .value("lastname")
                .value("firstname")
                .from("persons")
                .where("age").isGreaterThan(18)
                .build();

        assertEquals("SELECT lastname, firstname FROM persons WHERE age > 18;", query);
    }


}
