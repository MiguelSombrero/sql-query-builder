package builder.clause;

import builder.query.select.table.Table;
import database.row.Row;
import query.dql.SelectQuery;
import testutils.DatabaseConnection;
import testutils.DatabaseTestBaseClass;
import builder.query.QueryFactory;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static builder.clause.ConditionClauseBuilder.valueOf;
import static org.junit.Assert.assertEquals;

public class ConditionTest extends DatabaseTestBaseClass {
    private Table baseQuery;

    @Before
    public void setUpQuery() {
        initializeDatabase();

        QueryFactory queryFactory = new QueryFactory(DatabaseConnection.getDataSource());

        this.baseQuery = queryFactory
                .select()
                .column("firstname")
                .from()
                    .table("person");
    }

    @Test
    public void testWhereAndCondition() throws SQLException {
        SelectQuery query = this.baseQuery
                .where(valueOf("age").greaterThan(30)
                        .and(valueOf("birthdate").greaterThan("1980-02-28 21:00:00")))
                .build();

        assertEquals("SELECT firstname FROM person WHERE age > 30 AND birthdate > '1980-02-28 21:00:00'", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 1);
    }

    @Test
    public void testWhereOrCondition() throws SQLException {
        SelectQuery query = this.baseQuery
                .where(valueOf("age").greaterThan(30)
                        .or(valueOf("birthdate").greaterThan("1980-02-28 21:00:00")))
                .build();

        assertEquals("SELECT firstname FROM person WHERE age > 30 OR birthdate > '1980-02-28 21:00:00'", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 2);
    }

    @Test
    public void testWhereNegativeCondition() throws SQLException {
        SelectQuery query = this.baseQuery
                .where(valueOf("age").greaterThan(-100)
                        .and(valueOf("age").lesserThan(-18)))
                .build();

        assertEquals("SELECT firstname FROM person WHERE age > -100 AND age < -18", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 0);
    }

    @Test
    public void testWhereAndOrConditions() throws SQLException {
        SelectQuery query = this.baseQuery
                .where(valueOf("age").greaterThan(30)
                        .and(valueOf("birthdate").greaterThan("1980-02-28 21:00:00"))
                        .or(valueOf("birthdate").lesserThan("1960-02-28 21:00:00")))
                .build();

        assertEquals("SELECT firstname FROM person WHERE age > 30 AND birthdate > '1980-02-28 21:00:00' OR birthdate < '1960-02-28 21:00:00'", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 1);
    }

    @Test
    public void testWhereOrSubCondition() throws SQLException {
        SelectQuery query = this.baseQuery
                .where(valueOf("age").lesserThan(18)
                        .orSub(valueOf("age").greaterThan(37)
                                .and(valueOf("age").lesserThan(60))))
                .build();

        assertEquals("SELECT firstname FROM person WHERE age < 18 OR (age > 37 AND age < 60)", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testOrSubWithSQLInjection() {
        this.baseQuery
                .where(valueOf("age").lesserThan(18)
                        .orSub(valueOf(";DROP").greaterThan(50)
                                .and(valueOf("age").lesserThan(60))))
                .build();
    }
}
