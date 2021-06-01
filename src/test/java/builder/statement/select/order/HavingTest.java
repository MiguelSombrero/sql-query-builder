package builder.statement.select.order;

import database.DatabaseConnection;
import database.DatabaseTestBaseClass;
import factory.SelectQueryFactory;
import org.junit.Before;
import org.junit.Test;
import query.Query;

import java.sql.SQLException;

import static factory.HavingClauseFactory.*;
import static org.junit.Assert.assertEquals;

public class HavingTest extends DatabaseTestBaseClass {
    private Having having;

    @Before
    public void setUpQuery() {
        initializeDatabase();

        SelectQueryFactory selectQueryFactory = new SelectQueryFactory(DatabaseConnection.getDataSource());

        this.having = selectQueryFactory
                .select()
                .column("*")
                .from()
                    .table("person")
                .groupBy()
                    .column("age")
                    .column("firstname")
                    .column("lastname");
    }

    @Test
    public void testHavingCount() throws SQLException {
        Query query = this.having
                .having(count("age").equals(100))
                .build();

        assertEquals("SELECT * FROM person GROUP BY age, firstname, lastname HAVING COUNT(age) = 100", query.toString());
    }

    @Test
    public void testHavingSum() throws SQLException {
        Query query = this.having
                .having(sum("age").equals(100))
                .build();

        assertEquals("SELECT * FROM person GROUP BY age, firstname, lastname HAVING SUM(age) = 100", query.toString());
    }

    @Test
    public void testHavingAvg() throws SQLException {
        Query query = this.having
                .having(avg("age").equals(100))
                .build();

        assertEquals("SELECT * FROM person GROUP BY age, firstname, lastname HAVING AVG(age) = 100", query.toString());
    }

    @Test
    public void testHavingMax() throws SQLException {
        Query query = this.having
                .having(max("age").equals(100))
                .build();

        assertEquals("SELECT * FROM person GROUP BY age, firstname, lastname HAVING MAX(age) = 100", query.toString());
    }

    @Test
    public void testHavingMin() throws SQLException {
        Query query = this.having
                .having(min("age").equals(100))
                .build();

        assertEquals("SELECT * FROM person GROUP BY age, firstname, lastname HAVING MIN(age) = 100", query.toString());
    }

    @Test
    public void testHavingMultipleConditions() throws SQLException {
        Query query = this.having
                .having(max("age").equals(100)
                    .and(min("age").equals(20))
                    .or(avg("age").equals(60)))
                .build();

        assertEquals("SELECT * FROM person GROUP BY age, firstname, lastname HAVING MAX(age) = 100 AND MIN(age) = 20 OR AVG(age) = 60", query.toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGroupByHavingWithSQLInjection() {
        this.having
                .having(count(";DROP").equals(100))
                .build();
    }
}
