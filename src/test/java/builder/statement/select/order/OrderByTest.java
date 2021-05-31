package builder.statement.select.order;

import builder.statement.select.table.Table;
import database.DatabaseConnection;
import database.DatabaseTestBaseClass;
import factory.QueryFactory;
import factory.SelectQueryFactory;
import org.junit.Before;
import org.junit.Test;
import query.Query;

import java.sql.SQLException;

import static factory.WhereClauseFactory.valueOf;
import static org.junit.Assert.assertEquals;

public class OrderByTest extends DatabaseTestBaseClass {
    private Table table;

    @Before
    public void setUpQuery() throws SQLException {
        initializeDatabase();

        SelectQueryFactory selectQueryFactory = new SelectQueryFactory(DatabaseConnection.getConnection());

        this.table = selectQueryFactory
                .select()
                    .column("*")
                .from()
                    .table("person");
    }

    @Test
    public void testOrderBy() throws SQLException {
        Query query = table
                .orderBy()
                    .column("firstname")
                .build();

        assertEquals("SELECT * FROM person ORDER BY firstname", query.toString());
    }

    @Test
    public void testOrderByWhere() throws SQLException {
        Query query = table
                .where(valueOf("age").greaterThan(18))
                .orderBy()
                .column("firstname")
                .build();

        assertEquals("SELECT * FROM person WHERE age > 18 ORDER BY firstname", query.toString());
    }

    @Test
    public void testOrderByGroupBy() throws SQLException {
        Query query = table
                .groupBy()
                    .column("age")
                .orderBy()
                    .column("firstname")
                .build();

        assertEquals("SELECT * FROM person GROUP BY age ORDER BY firstname", query.toString());
    }

    @Test
    public void testMultipleOrderByGroupBy() throws SQLException {
        Query query = table
                .groupBy()
                    .column("age")
                .orderBy()
                    .column("firstname")
                    .column("lastname")
                    .column("age")
                .build();

        assertEquals("SELECT * FROM person GROUP BY age ORDER BY firstname, lastname, age", query.toString());
    }

    @Test
    public void testMultipleOrderByGroupByWithAscDesc() throws SQLException {
        Query query = table
                .groupBy()
                    .column("age")
                .orderBy()
                    .column("firstname").asc()
                    .column("lastname").desc()
                    .column("age").asc()
                .build();

        assertEquals("SELECT * FROM person GROUP BY age ORDER BY firstname ASC, lastname DESC, age ASC", query.toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testOrderByFirstColumnWithSQLInjection() {
        table
                .orderBy()
                    .column(";DROP").asc()
                .build();

    }

    @Test(expected = IllegalArgumentException.class)
    public void testOrderByColumnWithSQLInjection() {
        table
                .orderBy()
                    .column("age")
                    .column(";DROP").asc()
                .build();

    }
}
