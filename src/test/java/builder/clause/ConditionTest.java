package builder.clause;

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

public class ConditionTest extends DatabaseTestBaseClass {
    private Table baseQuery;

    @Before
    public void setUpQuery() {
        initializeDatabase();

        SQLQueryBuilder SQLQueryBuilder = new SQLQueryBuilder(DatabaseConnection.getDataSource());

        this.baseQuery = SQLQueryBuilder
                .select()
                .column("firstname")
                .from()
                    .table("person");
    }

    @Test
    public void testWhereAndCondition() throws SQLException {
        SelectQuery query = this.baseQuery
                .where(valueOf("age").greaterThanInteger(30)
                        .and(valueOf("birthdate").greaterThanTimestamp("1980-02-28 21:00:10")))
                .build();

        assertEquals("SELECT firstname FROM person WHERE age > 30 AND birthdate > '1980-02-28 21:00:10.0'", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 1);
    }

    @Test
    public void testWhereOrCondition() throws SQLException {
        SelectQuery query = this.baseQuery
                .where(valueOf("age").greaterThanInteger(30)
                        .or(valueOf("birthdate").greaterThanTimestamp("1980-02-28 21:00:02")))
                .build();

        assertEquals("SELECT firstname FROM person WHERE age > 30 OR birthdate > '1980-02-28 21:00:02.0'", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 2);
    }

    @Test
    public void testWhereNegativeCondition() throws SQLException {
        SelectQuery query = this.baseQuery
                .where(valueOf("age").greaterThanInteger(-100)
                        .and(valueOf("age").lesserThanInteger(-18)))
                .build();

        assertEquals("SELECT firstname FROM person WHERE age > -100 AND age < -18", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 0);
    }

    @Test
    public void testWhereAndOrConditions() throws SQLException {
        SelectQuery query = this.baseQuery
                .where(valueOf("age").greaterThanInteger(30)
                        .and(valueOf("birthdate").greaterThanTimestamp("1980-02-28 21:00:05"))
                        .or(valueOf("birthdate").lesserThanTimestamp("1960-02-28 21:00:05")))
                .build();

        assertEquals("SELECT firstname FROM person WHERE age > 30 AND birthdate > '1980-02-28 21:00:05.0' OR birthdate < '1960-02-28 21:00:05.0'", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 1);
    }

    @Test
    public void testWhereOrSubCondition() throws SQLException {
        SelectQuery query = this.baseQuery
                .where(valueOf("age").lesserThanInteger(18)
                        .orSub(valueOf("age").greaterThanInteger(37)
                                .and(valueOf("age").lesserThanInteger(60))))
                .build();

        assertEquals("SELECT firstname FROM person WHERE age < 18 OR (age > 37 AND age < 60)", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testOrSubWithSQLInjection() {
        this.baseQuery
                .where(valueOf("age").lesserThanInteger(18)
                        .orSub(valueOf(";DROP").greaterThanInteger(50)
                                .and(valueOf("age").lesserThanInteger(60))))
                .build();
    }
}
