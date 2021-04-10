package builder.clause;

import factory.QueryFactory;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class ConditionTest {

    @Test
    public void testConditionStringEquals() {
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
    public void testConditionIntegerEquals() {
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
    public void testConditionStringGreaterThan() {
        String query = QueryFactory
                .select()
                .value("lastname")
                .value("firstname")
                .from("persons")
                .where("birthdate").greaterThan("2020-02-28T21:00:00.000")
                .build();

        assertEquals("SELECT lastname, firstname FROM persons WHERE birthdate > '2020-02-28T21:00:00.000';", query);
    }

    @Test
    public void testConditionIntegerGreaterThan() {
        String query = QueryFactory
                .select()
                .value("lastname")
                .value("firstname")
                .from("persons")
                .where("age").greaterThan(18)
                .build();

        assertEquals("SELECT lastname, firstname FROM persons WHERE age > 18;", query);
    }

    @Test
    public void testConditionStringLesserThan() {
        String query = QueryFactory
                .select()
                .value("lastname")
                .value("firstname")
                .from("persons")
                .where("birthdate").lesserThan("2020-02-28T21:00:00.000")
                .build();

        assertEquals("SELECT lastname, firstname FROM persons WHERE birthdate < '2020-02-28T21:00:00.000';", query);
    }

    @Test
    public void testConditionIntegerLesserThan() {
        String query = QueryFactory
                .select()
                .value("lastname")
                .value("firstname")
                .from("persons")
                .where("age").lesserThan(18)
                .build();

        assertEquals("SELECT lastname, firstname FROM persons WHERE age < 18;", query);
    }
}
