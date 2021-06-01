package builder.statement.select.order;

import builder.statement.select.table.Table;
import database.DatabaseConnection;
import database.DatabaseTestBaseClass;
import factory.SelectQueryFactory;
import org.junit.Before;
import org.junit.Test;
import query.Query;

import java.sql.SQLException;

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
        Query query = table
                .groupBy()
                    .column("age")
                .build();

        assertEquals("SELECT * FROM person GROUP BY age", query.toString());
    }

    @Test
    public void testMultipleGroupBy() throws SQLException {
        Query query = table
                .groupBy()
                    .column("age")
                    .column("firstname")
                    .column("lastname")
                .build();

        assertEquals("SELECT * FROM person GROUP BY age, firstname, lastname", query.toString());
    }

    @Test
    public void testGroupByWhere() throws SQLException {
        Query query = this.table
                .where(valueOf("age").greaterThan(18))
                .groupBy()
                    .column("lastname")
                .build();

        assertEquals("SELECT * FROM person WHERE age > 18 GROUP BY lastname", query.toString());
    }

    @Test
    public void testMultipleGroupByHaving() throws SQLException {
        Query query = table
                .groupBy()
                    .column("age")
                    .column("firstname")
                    .column("lastname")
                .having(count("age").equals(100))
                .build();

        assertEquals("SELECT * FROM person GROUP BY age, firstname, lastname HAVING COUNT(age) = 100", query.toString());
    }

    @Test
    public void testMultipleGroupByHavingAndOrdering() throws SQLException {
        Query query = table
                .groupBy()
                    .column("age")
                    .column("firstname")
                    .column("lastname")
                .having(count("age").equals(100))
                .orderBy()
                    .column("age").asc()
                .build();

        assertEquals("SELECT * FROM person GROUP BY age, firstname, lastname HAVING COUNT(age) = 100 ORDER BY age ASC", query.toString());
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
