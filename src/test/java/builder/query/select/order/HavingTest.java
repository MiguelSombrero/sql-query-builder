package builder.query.select.order;

import database.row.Row;
import query.dql.SelectQuery;
import testutils.DatabaseConnection;
import testutils.DatabaseTestBaseClass;
import builder.query.QueryFactory;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static builder.clause.ConditionClauseBuilder.*;
import static org.junit.Assert.assertEquals;

public class HavingTest extends DatabaseTestBaseClass {
    private Having having;

    @Before
    public void setUpQuery() {
        initializeDatabase();

        QueryFactory queryFactory = new QueryFactory(DatabaseConnection.getDataSource());

        this.having = queryFactory
                .select()
                    .all()
                .from()
                    .table("person")
                .groupBy()
                    .column("age")
                    .column("firstname")
                    .column("lastname");
    }

    @Test
    public void testHavingCount() throws SQLException {
        SelectQuery query = this.having
                .having(count("age").equals(100))
                .build();

        assertEquals("SELECT * FROM person GROUP BY age, firstname, lastname HAVING COUNT(age) = 100", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 0);
    }

    @Test
    public void testHavingSum() throws SQLException {
        SelectQuery query = this.having
                .having(sum("age").equals(100))
                .build();

        assertEquals("SELECT * FROM person GROUP BY age, firstname, lastname HAVING SUM(age) = 100", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 0);
    }

    @Test
    public void testHavingAvg() throws SQLException {
        SelectQuery query = this.having
                .having(avg("age").equals(100))
                .build();

        assertEquals("SELECT * FROM person GROUP BY age, firstname, lastname HAVING AVG(age) = 100", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 0);
    }

    @Test
    public void testHavingMax() throws SQLException {
        SelectQuery query = this.having
                .having(max("age").equals(100))
                .build();

        assertEquals("SELECT * FROM person GROUP BY age, firstname, lastname HAVING MAX(age) = 100", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 0);
    }

    @Test
    public void testHavingMin() throws SQLException {
        SelectQuery query = this.having
                .having(min("age").equals(100))
                .build();

        assertEquals("SELECT * FROM person GROUP BY age, firstname, lastname HAVING MIN(age) = 100", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 0);
    }

    @Test
    public void testHavingMultipleConditions() throws SQLException {
        SelectQuery query = this.having
                .having(max("age").equals(100)
                    .and(min("age").equals(20))
                    .or(avg("age").equals(60)))
                .build();

        assertEquals("SELECT * FROM person GROUP BY age, firstname, lastname HAVING MAX(age) = 100 AND MIN(age) = 20 OR AVG(age) = 60", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGroupByHavingWithSQLInjection() {
        this.having
                .having(count(";DROP").equals(100))
                .build();
    }
}
