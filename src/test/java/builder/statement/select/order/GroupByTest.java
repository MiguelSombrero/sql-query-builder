package builder.statement.select.order;

import builder.statement.select.table.Table;
import database.Row;
import query.SelectQuery;
import testutils.DatabaseConnection;
import testutils.DatabaseTestBaseClass;
import factory.SelectQueryFactory;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static factory.HavingClauseFactory.count;
import static factory.WhereClauseFactory.valueOf;
import static org.junit.Assert.assertEquals;

public class GroupByTest extends DatabaseTestBaseClass {
    private Table table;

    @Before
    public void setUpQuery() {
        initializeDatabase();

        SelectQueryFactory selectQueryFactory = new SelectQueryFactory(DatabaseConnection.getDataSource());

        this.table = selectQueryFactory
                .select()
                .column("*")
                .from()
                    .table("person");
    }

    @Test
    public void testGroupBy() throws SQLException {
        SelectQuery query = table
                .groupBy()
                    .column("age")
                .build();

        assertEquals("SELECT * FROM person GROUP BY age", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 3);
        assertRowLength(result, 5);
    }

    @Test
    public void testMultipleGroupBy() throws SQLException {
        SelectQuery query = table
                .groupBy()
                    .column("age")
                    .column("firstname")
                    .column("lastname")
                .build();

        assertEquals("SELECT * FROM person GROUP BY age, firstname, lastname", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 3);
        assertRowLength(result, 5);
    }

    @Test
    public void testGroupByWhere() throws SQLException {
        SelectQuery query = this.table
                .where(valueOf("age").greaterThan(18))
                .groupBy()
                    .column("lastname")
                .build();

        assertEquals("SELECT * FROM person WHERE age > 18 GROUP BY lastname", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 3);
        assertRowLength(result, 5);
    }

    @Test
    public void testMultipleGroupByHaving() throws SQLException {
        SelectQuery query = table
                .groupBy()
                    .column("age")
                    .column("firstname")
                    .column("lastname")
                .having(count("age").equals(100))
                .build();

        assertEquals("SELECT * FROM person GROUP BY age, firstname, lastname HAVING COUNT(age) = 100", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 0);
    }

    @Test
    public void testMultipleGroupByHavingAndOrdering() throws SQLException {
        SelectQuery query = table
                .groupBy()
                    .column("age")
                    .column("firstname")
                    .column("lastname")
                .having(count("age").equals(100))
                .orderBy()
                    .column("age").asc()
                .build();

        assertEquals("SELECT * FROM person GROUP BY age, firstname, lastname HAVING COUNT(age) = 100 ORDER BY age ASC", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 0);
    }

    @Test(expected =IllegalArgumentException.class)
    public void testGroupByWithSQLInjection() {
        table
                .groupBy()
                .column(";DROP")
                .build();
    }

    @Test(expected =IllegalArgumentException.class)
    public void testGroupByWithHavingColumnSQLInjection() {
        table
                .groupBy()
                    .column("firstname")
                    .column(";DROP")
                .build();
    }
}
