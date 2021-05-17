package builder.statement.select.order;

import builder.statement.select.table.Table;
import database.DatabaseTestBaseClass;
import factory.QueryFactory;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static factory.HavingClauseFactory.count;
import static factory.WhereClauseFactory.valueOf;
import static org.junit.Assert.assertEquals;

public class GroupByTest extends DatabaseTestBaseClass {
    private Table table;

    @Before
    public void setUpQuery() {
        initializeDatabase();

        this.table = QueryFactory
                .select()
                .column("*")
                .from()
                    .table("person");
    }

    @Test
    public void testGroupBy() throws SQLException {
        String query = table
                .groupBy()
                    .column("age")
                .build();

        assertEquals("SELECT * FROM person GROUP BY age", query);
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

        assertEquals("SELECT * FROM person GROUP BY age, firstname, lastname", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testGroupByWhere() throws SQLException {
        String query = this.table
                .where(valueOf("age").greaterThan(18))
                .groupBy()
                    .column("lastname")
                .build();

        Assert.assertEquals("SELECT * FROM person WHERE age > 18 GROUP BY lastname", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testMultipleGroupByHaving() throws SQLException {
        String query = table
                .groupBy()
                    .column("age")
                    .column("firstname")
                    .column("lastname")
                .having(count("age").equals(100))
                .build();

        assertEquals("SELECT * FROM person GROUP BY age, firstname, lastname HAVING COUNT(age) = 100", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testMultipleGroupByHavingAndOrdering() throws SQLException {
        String query = table
                .groupBy()
                    .column("age")
                    .column("firstname")
                    .column("lastname")
                .having(count("age").equals(100))
                .orderBy()
                    .column("age").asc()
                .build();

        assertEquals("SELECT * FROM person GROUP BY age, firstname, lastname HAVING COUNT(age) = 100 ORDER BY age ASC", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test(expected =IllegalArgumentException.class)
    public void testGroupByWithSQLInjection() {
        table
                .groupBy()
                .column(";DROP")
                .having(count("age").equals(100))
                .build();
    }
}
