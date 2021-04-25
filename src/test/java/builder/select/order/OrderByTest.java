package builder.select.order;

import factory.QueryFactory;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class OrderByTest {
    private GroupByTemplate groupBy;

    @Before
    public void setUpQuery() {
        this.groupBy = QueryFactory
                .select()
                    .field("*")
                .from("person")
                .groupBy()
                    .column("age");
    }

    @Test
    public void testOrderBy() {
        String query = groupBy
                .orderBy()
                    .column("firstname")
                .build();

        assertEquals("SELECT * FROM person GROUP BY age ORDER BY firstname;", query);
    }

    @Test
    public void testMultipleOrderBy() {
        String query = groupBy
                .orderBy()
                    .column("firstname")
                    .column("lastname")
                    .column("age")
                .build();

        assertEquals("SELECT * FROM person GROUP BY age ORDER BY firstname, lastname, age;", query);
    }
}
