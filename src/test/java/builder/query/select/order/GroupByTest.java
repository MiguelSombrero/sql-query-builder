package builder.query.select.order;

import builder.query.select.table.Table;
import database.row.Row;
import query.dql.SelectQuery;
import testutils.DatabaseConnection;
import testutils.DatabaseTestBaseClass;
import builder.query.SQLQueryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static builder.clause.ConditionClauseBuilder.*;
import static org.junit.Assert.assertEquals;

public class GroupByTest extends DatabaseTestBaseClass {
    private Table baseQuery;

    @Before
    public void setUpQuery() {
        initializeDatabase();

        SQLQueryBuilder SQLQueryBuilder = new SQLQueryBuilder(DatabaseConnection.getDataSource());

        this.baseQuery = SQLQueryBuilder
                .select()
                    .all()
                .from()
                    .table("person");
    }

    @Test
    public void testGroupBy() throws SQLException {
        SelectQuery query = baseQuery
                .groupBy()
                    .column("age")
                .build();

        assertEquals("SELECT * FROM person GROUP BY age", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 3);
        assertColumnCount(result, 5);
    }

    @Test
    public void testMultipleGroupBy() throws SQLException {
        SelectQuery query = baseQuery
                .groupBy()
                    .column("age")
                    .column("firstname")
                    .column("lastname")
                .build();

        assertEquals("SELECT * FROM person GROUP BY age, firstname, lastname", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 3);
        assertColumnCount(result, 5);
    }

    @Test
    public void testGroupByWhere() throws SQLException {
        SelectQuery query = this.baseQuery
                .where(valueOf("age").greaterThan(18))
                .groupBy()
                    .column("lastname")
                .build();

        assertEquals("SELECT * FROM person WHERE age > 18 GROUP BY lastname", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 3);
        assertColumnCount(result, 5);
    }

    @Test
    public void testMultipleGroupByHaving() throws SQLException {
        SelectQuery query = baseQuery
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
        SelectQuery query = baseQuery
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
        baseQuery
                .groupBy()
                .column(";DROP")
                .build();
    }

    @Test(expected =IllegalArgumentException.class)
    public void testGroupByWithHavingColumnSQLInjection() {
        baseQuery
                .groupBy()
                    .column("firstname")
                    .column(";DROP")
                .build();
    }
}
