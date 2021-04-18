package builder.conjunction;

import builder.table.Table;
import factory.QueryFactory;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class ConjunctionTest {
    private Table table;

    @Before
    public void setUp() {
        this.table = QueryFactory
                .select()
                .field("firstname")
                .from("persons");
    }

    @Test
    public void testWhereAndCondition() {
        String query = this.table
                .where("age").greaterThan(18)
                .and("birthdate").greaterThan("2020-02-28T21:00:00.000")
                .build();

        assertEquals("SELECT firstname FROM persons WHERE age > 18 AND birthdate > '2020-02-28T21:00:00.000';", query);
    }

    @Test
    public void testWhereOrCondition() {
        String query = this.table
                .where("age").greaterThan(18)
                .or("birthdate").greaterThan("2020-02-28T21:00:00.000")
                .build();

        assertEquals("SELECT firstname FROM persons WHERE age > 18 OR birthdate > '2020-02-28T21:00:00.000';", query);
    }

    @Test
    public void testWhereAndOrConditions() {
        String query = this.table
                .where("age").greaterThan(18)
                .and("birthdate").greaterThan("2020-02-28T21:00:00.000")
                .or("birthdate").lesserThan("2018-02-28T21:00:00.000")
                .build();

        assertEquals("SELECT firstname FROM persons WHERE age > 18 AND birthdate > '2020-02-28T21:00:00.000' OR birthdate < '2018-02-28T21:00:00.000';", query);
    }
}
