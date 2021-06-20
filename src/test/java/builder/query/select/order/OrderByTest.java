package builder.query.select.order;

import builder.query.SQLQueryBuilder;
import builder.query.select.table.Table;
import database.row.Row;
import query.SelectQuery;
import testutils.DatabaseConnection;
import testutils.DatabaseTestBaseClass;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static builder.clause.ConditionClauseBuilder.valueOf;
import static org.junit.Assert.assertEquals;

public class OrderByTest extends DatabaseTestBaseClass {
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
    public void testOrderBy() throws SQLException {
        SelectQuery query = baseQuery
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
        SelectQuery query = baseQuery
                .where(valueOf("age").greaterThanInteger(18))
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
        SelectQuery query = baseQuery
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
        SelectQuery query = baseQuery
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
        SelectQuery query = baseQuery
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
}
