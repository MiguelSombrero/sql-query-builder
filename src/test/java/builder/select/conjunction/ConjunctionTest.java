package builder.select.conjunction;

import builder.select.table.Table;
import database.DatabaseTestBaseClass;
import factory.QueryFactory;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static junit.framework.Assert.assertEquals;

public class ConjunctionTest extends DatabaseTestBaseClass {
    private Table table;

    @Before
    public void setUpQuery() {
        this.table = QueryFactory
                .select()
                .field("firstname")
                .from("person");
    }

    @Test
    public void testWhereAndCondition() throws SQLException {
        String query = this.table
                .where("age").greaterThan(18)
                .and("birthdate").greaterThan("2020-02-28T21:00:00.000")
                .build();

        assertEquals("SELECT firstname FROM person WHERE age > 18 AND birthdate > '2020-02-28T21:00:00.000';", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testWhereOrCondition() throws SQLException {
        String query = this.table
                .where("age").greaterThan(18)
                .or("birthdate").greaterThan("2020-02-28T21:00:00.000")
                .build();

        assertEquals("SELECT firstname FROM person WHERE age > 18 OR birthdate > '2020-02-28T21:00:00.000';", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testWhereAndOrConditions() throws SQLException {
        String query = this.table
                .where("age").greaterThan(18)
                .and("birthdate").greaterThan("2020-02-28T21:00:00.000")
                .or("birthdate").lesserThan("2018-02-28T21:00:00.000")
                .build();

        assertEquals("SELECT firstname FROM person WHERE age > 18 AND birthdate > '2020-02-28T21:00:00.000' OR birthdate < '2018-02-28T21:00:00.000';", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testGroupBy() throws SQLException {
        String query = this.table
                .where("age").greaterThan(18)
                .groupBy().column("lastname")
                .build();

        assertEquals("SELECT firstname FROM person WHERE age > 18 GROUP BY lastname;", query);
        assertThatQueryIsValidSQL(query);
    }
}
