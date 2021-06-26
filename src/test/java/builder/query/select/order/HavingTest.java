package builder.query.select.order;

import builder.query.SQLQueryBuilder;
import database.row.Row;
import query.SelectQuery;
import testutils.DatabaseConnection;
import testutils.DatabaseTestBaseClass;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static builder.clause.ConditionClauseBuilder.*;
import static org.junit.Assert.assertEquals;

public class HavingTest extends DatabaseTestBaseClass {
    private Having baseQuery;

    @Before
    public void setUpQuery() {
        initializeDatabase();

        SQLQueryBuilder sQLQueryBuilder = new SQLQueryBuilder(DatabaseConnection.getH2DataSource());

        this.baseQuery = sQLQueryBuilder
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
        SelectQuery query = this.baseQuery
                .having(count("age").equalsInteger(100))
                .build();

        assertEquals("SELECT * FROM person GROUP BY age, firstname, lastname HAVING COUNT(age) = 100", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 0);
    }

    @Test
    public void testHavingSum() throws SQLException {
        SelectQuery query = this.baseQuery
                .having(sum("age").equalsInteger(100))
                .build();

        assertEquals("SELECT * FROM person GROUP BY age, firstname, lastname HAVING SUM(age) = 100", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 0);
    }

    @Test
    public void testHavingAvg() throws SQLException {
        SelectQuery query = this.baseQuery
                .having(avg("age").equalsInteger(100))
                .build();

        assertEquals("SELECT * FROM person GROUP BY age, firstname, lastname HAVING AVG(age) = 100", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 0);
    }

    @Test
    public void testHavingMax() throws SQLException {
        SelectQuery query = this.baseQuery
                .having(max("age").equalsInteger(100))
                .build();

        assertEquals("SELECT * FROM person GROUP BY age, firstname, lastname HAVING MAX(age) = 100", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 0);
    }

    @Test
    public void testHavingMin() throws SQLException {
        SelectQuery query = this.baseQuery
                .having(min("age").equalsInteger(100))
                .build();

        assertEquals("SELECT * FROM person GROUP BY age, firstname, lastname HAVING MIN(age) = 100", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 0);
    }

    @Test
    public void testHavingMultipleConditions() throws SQLException {
        SelectQuery query = this.baseQuery
                .having(max("age").equalsInteger(100)
                    .and(min("age").equalsInteger(20))
                    .or(avg("age").equalsInteger(60)))
                .build();

        assertEquals("SELECT * FROM person GROUP BY age, firstname, lastname HAVING MAX(age) = 100 AND MIN(age) = 20 OR AVG(age) = 60", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 0);
    }
}
