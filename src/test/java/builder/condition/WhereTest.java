package builder.condition;

import builder.statement.select.table.Table;
import testutils.DatabaseConnection;
import testutils.DatabaseTestBaseClass;
import factory.SelectQueryFactory;
import org.junit.Before;
import org.junit.Test;
import query.Query;

import java.sql.SQLException;

import static factory.WhereClauseFactory.valueOf;
import static org.junit.Assert.assertEquals;

public class WhereTest extends DatabaseTestBaseClass {
    private Table table;

    @Before
    public void setUpQuery() {
        initializeDatabase();

        SelectQueryFactory selectQueryFactory = new SelectQueryFactory(DatabaseConnection.getDataSource());

        this.table = selectQueryFactory
                .select()
                .column("firstname")
                .from()
                    .table("person");
    }

    @Test
    public void testWhereAndCondition() throws SQLException {
        Query query = this.table
                .where(valueOf("age").greaterThan(18)
                        .and(valueOf("birthdate").greaterThan("2020-02-28 21:00:00")))
                .build();

        assertEquals("SELECT firstname FROM person WHERE age > 18 AND birthdate > '2020-02-28 21:00:00'", query.toString());
    }

    @Test
    public void testWhereOrCondition() throws SQLException {
        Query query = this.table
                .where(valueOf("age").greaterThan(18)
                        .or(valueOf("birthdate").greaterThan("2020-02-28 21:00:00")))
                .build();

        assertEquals("SELECT firstname FROM person WHERE age > 18 OR birthdate > '2020-02-28 21:00:00'", query.toString());
    }

    @Test
    public void testWhereNegativeCondition() throws SQLException {
        Query query = this.table
                .where(valueOf("age").greaterThan(-100)
                        .and(valueOf("age").lesserThan(-18)))
                .build();

        assertEquals("SELECT firstname FROM person WHERE age > -100 AND age < -18", query.toString());
    }

    @Test
    public void testWhereAndOrConditions() throws SQLException {
        Query query = this.table
                .where(valueOf("age").greaterThan(18)
                        .and(valueOf("birthdate").greaterThan("2020-02-28 21:00:00"))
                        .or(valueOf("birthdate").lesserThan("2018-02-28 21:00:00")))
                .build();

        assertEquals("SELECT firstname FROM person WHERE age > 18 AND birthdate > '2020-02-28 21:00:00' OR birthdate < '2018-02-28 21:00:00'", query.toString());
    }

    @Test
    public void testWhereOrSubCondition() throws SQLException {
        Query query = this.table
                .where(valueOf("age").lesserThan(18)
                        .orSub(valueOf("age").greaterThan(50)
                                .and(valueOf("age").lesserThan(60))))
                .build();

        assertEquals("SELECT firstname FROM person WHERE age < 18 OR (age > 50 AND age < 60)", query.toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testOrSubWithSQLInjection() {
        this.table
                .where(valueOf("age").lesserThan(18)
                        .orSub(valueOf(";DROP").greaterThan(50)
                                .and(valueOf("age").lesserThan(60))))
                .build();
    }
}
