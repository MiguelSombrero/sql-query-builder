package builder.conjunction;

import builder.table.Table;
import factory.QueryFactory;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class ComparisonTest {
    private Table table;

    @Before
    public void setUp() {
        this.table = QueryFactory
                .select()
                .field("firstname")
                .from("persons");
    }

    @Test
    public void testConditionStringEquals() {
        String query = this.table
                .where("firstname").equals("Miika")
                .build();

        assertEquals("SELECT firstname FROM persons WHERE firstname = 'Miika';", query);
    }

    @Test
    public void testConditionIntegerEquals() {
        String query = this.table
                .where("age").equals(18)
                .build();

        assertEquals("SELECT firstname FROM persons WHERE age = 18;", query);
    }

    @Test
    public void testConditionStringGreaterThan() {
        String query = this.table
                .where("birthdate").greaterThan("2020-02-28T21:00:00.000")
                .build();

        assertEquals("SELECT firstname FROM persons WHERE birthdate > '2020-02-28T21:00:00.000';", query);
    }

    @Test
    public void testConditionIntegerGreaterThan() {
        String query = this.table
                .where("age").greaterThan(18)
                .build();

        assertEquals("SELECT firstname FROM persons WHERE age > 18;", query);
    }

    @Test
    public void testConditionStringGreaterThanOrEqual() {
        String query = this.table
                .where("birthdate").greaterThanOrEqual("2020-02-28T21:00:00.000")
                .build();

        assertEquals("SELECT firstname FROM persons WHERE birthdate >= '2020-02-28T21:00:00.000';", query);
    }

    @Test
    public void testConditionIntegerGreaterThanOrEqual() {
        String query = this.table
                .where("age").greaterThanOrEqual(18)
                .build();

        assertEquals("SELECT firstname FROM persons WHERE age >= 18;", query);
    }

    @Test
    public void testConditionStringLesserThan() {
        String query = this.table
                .where("birthdate").lesserThan("2020-02-28T21:00:00.000")
                .build();

        assertEquals("SELECT firstname FROM persons WHERE birthdate < '2020-02-28T21:00:00.000';", query);
    }

    @Test
    public void testConditionIntegerLesserThan() {
        String query = this.table
                .where("age").lesserThan(18)
                .build();

        assertEquals("SELECT firstname FROM persons WHERE age < 18;", query);
    }

    @Test
    public void testConditionStringLesserThanOrEqual() {
        String query = this.table
                .where("birthdate").lesserThanOrEqual("2020-02-28T21:00:00.000")
                .build();

        assertEquals("SELECT firstname FROM persons WHERE birthdate <= '2020-02-28T21:00:00.000';", query);
    }

    @Test
    public void testConditionIntegerLesserThanOrEqual() {
        String query = this.table
                .where("age").lesserThanOrEqual(18)
                .build();

        assertEquals("SELECT firstname FROM persons WHERE age <= 18;", query);
    }
}
