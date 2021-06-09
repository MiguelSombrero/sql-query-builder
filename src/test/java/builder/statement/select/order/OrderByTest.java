package builder.statement.select.order;

import builder.statement.select.table.Table;
import query.dql.Row;
import query.dql.SelectQuery;
import testutils.DatabaseConnection;
import testutils.DatabaseTestBaseClass;
import query.QueryFactory;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static builder.clause.WhereClauseFactory.valueOf;
import static org.junit.Assert.assertEquals;

public class OrderByTest extends DatabaseTestBaseClass {
    private Table table;

    @Before
    public void setUpQuery() {
        initializeDatabase();

        QueryFactory queryFactory = new QueryFactory(DatabaseConnection.getDataSource());

        this.table = queryFactory
                .select()
                    .all()
                .from()
                    .table("person");
    }

    @Test
    public void testOrderBy() throws SQLException {
        SelectQuery query = table
                .orderBy()
                    .column("firstname")
                .build();

        assertEquals("SELECT * FROM person ORDER BY firstname", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 3);
        assertColumnCount(result, 5);
    }

    @Test
    public void testOrderByWhere() throws SQLException {
        SelectQuery query = table
                .where(valueOf("age").greaterThan(18))
                .orderBy()
                .column("firstname")
                .build();

        assertEquals("SELECT * FROM person WHERE age > 18 ORDER BY firstname", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 3);
        assertColumnCount(result, 5);
    }

    @Test
    public void testOrderByGroupBy() throws SQLException {
        SelectQuery query = table
                .groupBy()
                    .column("age")
                .orderBy()
                    .column("firstname")
                .build();

        assertEquals("SELECT * FROM person GROUP BY age ORDER BY firstname", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 3);
        assertColumnCount(result, 5);
    }

    @Test
    public void testMultipleOrderByGroupBy() throws SQLException {
        SelectQuery query = table
                .groupBy()
                    .column("age")
                .orderBy()
                    .column("firstname")
                    .column("lastname")
                    .column("age")
                .build();

        assertEquals("SELECT * FROM person GROUP BY age ORDER BY firstname, lastname, age", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 3);
        assertColumnCount(result, 5);
    }

    @Test
    public void testMultipleOrderByGroupByWithAscDesc() throws SQLException {
        SelectQuery query = table
                .groupBy()
                    .column("age")
                .orderBy()
                    .column("firstname").asc()
                    .column("lastname").desc()
                    .column("age").asc()
                .build();

        assertEquals("SELECT * FROM person GROUP BY age ORDER BY firstname ASC, lastname DESC, age ASC", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 3);
        assertColumnCount(result, 5);
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
