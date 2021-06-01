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

public class NegationTest extends DatabaseTestBaseClass {
    private SelectQueryFactory selectQueryFactory;
    private Table table;

    @Before
    public void setUpQuery() throws SQLException {
        initializeDatabase();

        selectQueryFactory = new SelectQueryFactory(DatabaseConnection.getDataSource());

        this.table = selectQueryFactory
                .select()
                .column("firstname")
                .from()
                    .table("person");
    }

    @Test
    public void testWhereNotCondition() throws SQLException {
        Query query = this.table
                .where(valueOf("age").not().greaterThan(18))
                .build();

        assertEquals("SELECT firstname FROM person WHERE NOT age > 18", query.toString());
    }

    @Test
    public void testWhereNotAndNotConditions() throws SQLException {
        Query query = this.table
                .where(valueOf("age").not().greaterThan(18)
                        .and(valueOf("firstname").not().equals("Miika")))
                .build();

        assertEquals("SELECT firstname FROM person WHERE NOT age > 18 AND NOT firstname = 'Miika'", query.toString());
    }

    @Test
    public void testDoubleNot() throws SQLException {
        Query query = this.table
                .where(valueOf("age").not().isNotNull())
                .build();

        assertEquals("SELECT firstname FROM person WHERE NOT age IS NOT NULL", query.toString());
    }

    @Test
    public void testWhereNotBetween() throws SQLException {
        Query query = this.table
                .where(valueOf("age").not().isBetween(18, 20))
                .build();

        assertEquals("SELECT firstname FROM person WHERE NOT age BETWEEN 18 AND 20", query.toString());
    }

    @Test
    public void testWhereNotIn() throws SQLException {
        Query query = this.table
                .where(valueOf("age").not().isIn(30, 40, 50, 60))
                .build();

        assertEquals("SELECT firstname FROM person WHERE NOT age IN (30, 40, 50, 60)", query.toString());
    }

    @Test
    public void testWhereNotInSubQuery() throws SQLException {
        Query query = this.table
                .where(valueOf("lastname")
                        .not()
                        .isInSub(selectQueryFactory
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
