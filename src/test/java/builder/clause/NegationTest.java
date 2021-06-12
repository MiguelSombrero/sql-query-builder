package builder.clause;

import builder.statement.select.table.Table;
import database.row.Row;
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

public class NegationTest extends DatabaseTestBaseClass {
    private QueryFactory queryFactory;
    private Table baseQuery;

    @Before
    public void setUpQuery() {
        initializeDatabase();

        queryFactory = new QueryFactory(DatabaseConnection.getDataSource());

        this.baseQuery = queryFactory
                .select()
                .column("firstname")
                .from()
                    .table("person");
    }

    @Test
    public void testWhereNotCondition() throws SQLException {
        SelectQuery query = this.baseQuery
                .where(valueOf("age").not().greaterThan(32))
                .build();

        assertEquals("SELECT firstname FROM person WHERE NOT age > 32", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 1);
        assertColumnCount(result, 1);
    }

    @Test
    public void testWhereNotAndNotConditions() throws SQLException {
        SelectQuery query = this.baseQuery
                .where(valueOf("age").not().greaterThan(32)
                        .and(valueOf("firstname").not().equals("Miika")))
                .build();

        assertEquals("SELECT firstname FROM person WHERE NOT age > 32 AND NOT firstname = 'Miika'", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 1);
        assertColumnCount(result, 1);
    }

    @Test
    public void testDoubleNot() throws SQLException {
        SelectQuery query = this.baseQuery
                .where(valueOf("age").not().isNotNull())
                .build();

        assertEquals("SELECT firstname FROM person WHERE NOT age IS NOT NULL", query.toString());
    }

    @Test
    public void testWhereNotBetween() throws SQLException {
        SelectQuery query = this.baseQuery
                .where(valueOf("age").not().isBetween(18, 20))
                .build();

        assertEquals("SELECT firstname FROM person WHERE NOT age BETWEEN 18 AND 20", query.toString());
    }

    @Test
    public void testWhereNotIn() throws SQLException {
        SelectQuery query = this.baseQuery
                .where(valueOf("age").not().isIn(30, 40, 50, 60))
                .build();

        assertEquals("SELECT firstname FROM person WHERE NOT age IN (30, 40, 50, 60)", query.toString());
    }

    @Test
    public void testWhereNotInSubQuery() throws SQLException {
        SelectQuery query = this.baseQuery
                .where(valueOf("lastname")
                        .not()
                        .isInSub(queryFactory
                            .select()
                                .all()
                            .from()
                                .table("student")
                            .where(valueOf("age").greaterThan(20))))
                .build();

        assertEquals("SELECT firstname FROM person WHERE NOT lastname IN (SELECT * FROM student WHERE age > 20)", query.toString());
    }

    @Test
    public void testThatLastIndexOfReturnsIndexOfBlanksCorrectly() {
        StringBuilder builder = new StringBuilder("person WHERE NOT");
        int index = builder.lastIndexOf(" ");
        assertEquals(12, index);
    }

}
