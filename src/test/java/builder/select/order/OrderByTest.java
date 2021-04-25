package builder.select.order;

import database.DatabaseTestBaseClass;
import factory.QueryFactory;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

public class OrderByTest extends DatabaseTestBaseClass {
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
    public void testOrderBy() throws SQLException {
        String query = groupBy
                .orderBy()
                    .column("firstname")
                .build();

        assertEquals("SELECT * FROM person GROUP BY age ORDER BY firstname;", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testMultipleOrderBy() throws SQLException {
        String query = groupBy
                .orderBy()
                    .column("firstname")
                    .column("lastname")
                    .column("age")
                .build();

        assertEquals("SELECT * FROM person GROUP BY age ORDER BY firstname, lastname, age;", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testMultipleOrderByWithAscDesc() throws SQLException {
        String query = groupBy
                .orderBy()
                    .column("firstname").asc()
                    .column("lastname").desc()
                    .column("age").asc()
                .build();

        assertEquals("SELECT * FROM person GROUP BY age ORDER BY firstname ASC, lastname DESC, age ASC;", query);
        assertThatQueryIsValidSQL(query);
    }
}
