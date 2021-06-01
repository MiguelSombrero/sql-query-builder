package builder.statement.select.order;

import builder.statement.select.table.Table;
import database.Row;
import query.SelectQuery;
import testutils.DatabaseConnection;
import testutils.DatabaseTestBaseClass;
import factory.QueryFactory;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static factory.HavingClauseFactory.count;
import static factory.WhereClauseFactory.valueOf;
import static org.junit.Assert.assertEquals;

public class LimitTest extends DatabaseTestBaseClass {
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
    public void testLimitFromTable() throws SQLException {
        SelectQuery query = table
                .limit(2)
                .build();

        assertEquals("SELECT * FROM person LIMIT 2", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 2);
        assertRowLength(result, 5);
    }

    @Test
    public void testLimitFromTableWhere() throws SQLException {
        SelectQuery query = table
                .where(valueOf("age").greaterThan(18))
                .limit(2)
                .build();

        assertEquals("SELECT * FROM person WHERE age > 18 LIMIT 2", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 2);
        assertRowLength(result, 5);
    }

    @Test
    public void testLimitJoinTable() throws SQLException {
        SelectQuery query = table
                .leftJoin("course").on("person.id", "course.person_id")
                .limit(2)
                .build();

        assertEquals("SELECT * FROM person LEFT JOIN course ON person.id = course.person_id LIMIT 2", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 2);
        assertRowLength(result, 10);
    }

    @Test
    public void testLimitGroupBy() throws SQLException {
        SelectQuery query = table
                .groupBy()
                    .column("age")
                .limit(2)
                .build();

        assertEquals("SELECT * FROM person GROUP BY age LIMIT 2", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 2);
        assertRowLength(result, 5);
    }

    @Test
    public void testLimitGroupByHaving() throws SQLException {
        SelectQuery query = table
                .groupBy()
                    .column("age")
                .having(count("age").greaterThan(20))
                .limit(2)
                .build();

        assertEquals("SELECT * FROM person GROUP BY age HAVING COUNT(age) > 20 LIMIT 2", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 0);
    }

    @Test
    public void testLimitOrderBy() throws SQLException {
        SelectQuery query = table
                .groupBy()
                    .column("age")
                .orderBy()
                    .column("firstname")
                .limit(2)
                .build();

        assertEquals("SELECT * FROM person GROUP BY age ORDER BY firstname LIMIT 2", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 2);
        assertRowLength(result, 5);
    }

    @Test
    public void testLimitOffset() throws SQLException {
        SelectQuery query = table
                .limit(2)
                .offset(1)
                .build();

        assertEquals("SELECT * FROM person LIMIT 2 OFFSET 1", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 2);
        assertRowLength(result, 5);
        assertEquals("Kultanen", result.get(1).getStringFrom(3));
    }
}
