package builder.select.order;

import builder.select.table.Table;
import database.DatabaseTestBaseClass;
import factory.QueryFactory;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

public class GroupByTest extends DatabaseTestBaseClass {
    private Table table;

    @Before
    public void setUpQuery() {
        this.table = QueryFactory
                .select()
                .field("*")
                .from("person");
    }

    @Test
    public void testGroupBy() throws SQLException {
        String query = table
                .groupBy()
                    .column("age")
                .build();

        assertEquals("SELECT * FROM person GROUP BY age;", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testMultipleGroupBy() throws SQLException {
        String query = table
                .groupBy()
                    .column("age")
                    .column("firstname")
                    .column("lastname")
                .build();

        assertEquals("SELECT * FROM person GROUP BY age, firstname, lastname;", query);
        assertThatQueryIsValidSQL(query);
    }
}
