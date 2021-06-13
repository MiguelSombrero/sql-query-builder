package builder.query.select.order;

import builder.query.SQLQueryBuilder;
import builder.query.select.table.Table;
import database.row.Row;
import query.dql.SelectQuery;
import testutils.DatabaseConnection;
import testutils.DatabaseTestBaseClass;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static builder.clause.ConditionClauseBuilder.*;
import static org.junit.Assert.assertEquals;

public class LimitTest extends DatabaseTestBaseClass {
    private SQLQueryBuilder sqlQueryBuilder;
    private Table baseQuery;

    @Before
    public void setUpQuery() {
        initializeDatabase();

        this.sqlQueryBuilder = new SQLQueryBuilder(DatabaseConnection.getDataSource());

        this.baseQuery = sqlQueryBuilder
                .select()
                    .all()
                .from()
                    .table("person");
    }

    @Test
    public void testLimitFromTable() throws SQLException {
        SelectQuery query = baseQuery
                .limit(2)
                .build();

        assertEquals("SELECT * FROM person LIMIT 2", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 2);
        assertColumnCount(result, 5);
    }

    @Test
    public void testLimitFromTableWhere() throws SQLException {
        SelectQuery query = baseQuery
                .where(valueOf("age").greaterThan(18))
                .limit(2)
                .build();

        assertEquals("SELECT * FROM person WHERE age > 18 LIMIT 2", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 2);
        assertColumnCount(result, 5);
    }

    @Test
    public void testLimitJoinTable() throws SQLException {
        SelectQuery query = sqlQueryBuilder
                .select()
                    .column("person.id").alias("personId")
                    .column("person.firstname")
                    .column("course.id").alias("courseId")
                .from()
                    .table("person")
                .leftJoin("course").on("person.id", "course.person_id")
                .limit(2)
                .build();

        assertEquals("SELECT person.id AS personId, person.firstname, course.id AS courseId FROM person LEFT JOIN course ON person.id = course.person_id LIMIT 2", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 2);
        assertColumnCount(result, 3);
    }

    @Test
    public void testLimitGroupBy() throws SQLException {
        SelectQuery query = baseQuery
                .groupBy()
                    .column("age")
                .limit(2)
                .build();

        assertEquals("SELECT * FROM person GROUP BY age LIMIT 2", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 2);
        assertColumnCount(result, 5);
    }

    @Test
    public void testLimitGroupByHaving() throws SQLException {
        SelectQuery query = baseQuery
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
        SelectQuery query = baseQuery
                .groupBy()
                    .column("age")
                .orderBy()
                    .column("firstname")
                .limit(2)
                .build();

        assertEquals("SELECT * FROM person GROUP BY age ORDER BY firstname LIMIT 2", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 2);
        assertColumnCount(result, 5);
    }

    @Test
    public void testLimitOffset() throws SQLException {
        SelectQuery query = baseQuery
                .limit(2)
                .offset(1)
                .build();

        assertEquals("SELECT * FROM person LIMIT 2 OFFSET 1", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 2);
        assertColumnCount(result, 5);
        assertEquals("Kultanen", result.get(1).getString("lastname"));
    }
}
