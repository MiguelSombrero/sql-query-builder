package builder.select.order;

import builder.select.table.Table;
import factory.QueryFactory;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GroupByTest {
    private Table table;

    @Before
    public void setUpQuery() {
        this.table = QueryFactory
                .select()
                .field("*")
                .from("person");
    }

    @Test
    public void testGroupBy() {
        String query = table
                .groupBy()
                    .column("age")
                .build();

        assertEquals("SELECT * FROM person GROUP BY age;", query);
    }

    @Test
    public void testMultipleGroupBy() {
        String query = table
                .groupBy()
                    .column("age")
                    .column("firstname")
                    .column("lastname")
                .build();

        assertEquals("SELECT * FROM person GROUP BY age, firstname, lastname;", query);
    }
}
