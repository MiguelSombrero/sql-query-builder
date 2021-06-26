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

public class NegationTest extends DatabaseTestBaseClass {
    private SQLQueryBuilder SQLQueryBuilder;
    private Table baseQuery;

    @Before
    public void setUpQuery() {
        initializeDatabase();

        SQLQueryBuilder = new SQLQueryBuilder(DatabaseConnection.getH2DataSource());

        this.baseQuery = SQLQueryBuilder
                .select()
                .column("firstname")
                .from()
                    .table("person");
    }

    @Test
    public void testWhereNotCondition() throws SQLException {
        SelectQuery query = this.baseQuery
                .where(valueOf("age").not().greaterThanInteger(32))
                .build();

        assertEquals("SELECT firstname FROM person WHERE NOT age > 32", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 1);
        assertColumnCount(result, 1);
    }

    @Test
    public void testWhereNotAndNotConditions() throws SQLException {
        SelectQuery query = this.baseQuery
                .where(valueOf("age").not().greaterThanInteger(32)
                        .and(valueOf("firstname").not().equalsString("Miika")))
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

        List<Row> result = query.execute();

        assertRowCount(result, 0);
    }

    @Test
    public void testWhereNotBetween() throws SQLException {
        SelectQuery query = this.baseQuery
                .where(valueOf("age").not().isBetweenInteger(25, 35))
                .build();

        assertEquals("SELECT firstname FROM person WHERE NOT age BETWEEN 25 AND 35", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 1);
    }

    @Test
    public void testWhereNotIn() throws SQLException {
        SelectQuery query = this.baseQuery
                .where(valueOf("age").not().isInInteger(30, 40, 50, 60))
                .build();

        assertEquals("SELECT firstname FROM person WHERE NOT age IN (30, 40, 50, 60)", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 2);
    }

    @Test
    public void testWhereNotInSubQuery() throws SQLException {
        SelectQuery query = this.baseQuery
                .where(valueOf("lastname")
                        .not()
                        .isInSub(SQLQueryBuilder
                            .select()
                                .column("lastname")
                            .from()
                                .table("student")
                            .where(valueOf("age").greaterThanInteger(20))))
                .build();

        assertEquals("SELECT firstname FROM person WHERE NOT lastname IN (SELECT lastname FROM student WHERE age > 20)", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 2);
    }

    @Test
    public void testThatLastIndexOfReturnsIndexOfBlanksCorrectly() {
        StringBuilder builder = new StringBuilder("person WHERE NOT");
        int index = builder.lastIndexOf(" ");
        assertEquals(12, index);
    }

}
