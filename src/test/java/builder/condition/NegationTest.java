package builder.condition;

import builder.statement.select.table.Table;
import database.DatabaseTestBaseClass;
import factory.QueryFactory;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static factory.WhereClauseFactory.valueOf;
import static junit.framework.Assert.assertEquals;

public class NegationTest extends DatabaseTestBaseClass {
    private Table table;

    @Before
    public void setUpQuery() {
        initializeDatabase();

        this.table = QueryFactory
                .select()
                .column("firstname")
                .from()
                    .table("person");
    }

    @Test
    public void testWhereNotCondition() throws SQLException {
        String query = this.table
                .where(valueOf("age").not().greaterThan(18))
                .build();

        assertEquals("SELECT firstname FROM person WHERE NOT age > 18", query);

        logger.info(query);

        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testWhereNotAndNotConditions() throws SQLException {
        String query = this.table
                .where(valueOf("age").not().greaterThan(18)
                        .and(valueOf("firstname").not().equals("Miika")))
                .build();

        assertEquals("SELECT firstname FROM person WHERE NOT age > 18 AND NOT firstname = 'Miika'", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testDoubleNot() throws SQLException {
        String query = this.table
                .where(valueOf("age").not().isNotNull())
                .build();

        assertEquals("SELECT firstname FROM person WHERE NOT age IS NOT NULL", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testWhereNotBetween() throws SQLException {
        String query = this.table
                .where(valueOf("age").not().isBetween(18, 20))
                .build();

        assertEquals("SELECT firstname FROM person WHERE NOT age BETWEEN 18 AND 20", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testWhereNotIn() throws SQLException {
        String query = this.table
                .where(valueOf("age").not().isIn(30, 40, 50, 60))
                .build();

        assertEquals("SELECT firstname FROM person WHERE NOT age IN (30, 40, 50, 60)", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testWhereNotInSubQuery() throws SQLException {
        String query = this.table
                .where(valueOf("lastname")
                        .not()
                        .isInSub(QueryFactory
                        .select()
                        .column("*")
                        .from().table("student")
                        .where(valueOf("age").greaterThan(20))))
                .build();

        assertEquals("SELECT firstname FROM person WHERE NOT lastname IN (SELECT * FROM student WHERE age > 20)", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testThatLastIndexOfReturnsIndexOfBlanksCorrectly() {
        StringBuilder builder = new StringBuilder("person WHERE NOT");
        int index = builder.lastIndexOf(" ");
        assertEquals(12, index);
    }

}
