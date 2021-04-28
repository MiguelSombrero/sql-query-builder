package builder.select.conjunction;

import builder.select.table.Table;
import database.DatabaseTestBaseClass;
import factory.QueryFactory;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static junit.framework.Assert.assertEquals;

public class NegationTest extends DatabaseTestBaseClass {
    private Table table;

    @Before
    public void setUpQuery() {
        this.table = QueryFactory
                .select()
                .column("firstname")
                .from()
                    .table("person");
    }

    @Test
    public void testWhereNotCondition() throws SQLException {
        String query = this.table
                .where("age").not().greaterThan(18)
                .build();

        assertEquals("SELECT firstname FROM person WHERE NOT age > 18;", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testWhereNotAndNotConditions() throws SQLException {
        String query = this.table
                .where("age").not().greaterThan(18)
                .and("firstname").not().equals("Miika")
                .build();

        assertEquals("SELECT firstname FROM person WHERE NOT age > 18 AND NOT firstname = 'Miika';", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testThatLastIndexOfReturnsIndexOfBlanksCorrectly() {
        StringBuilder builder = new StringBuilder("person WHERE NOT");
        int index = builder.lastIndexOf(" ");
        assertEquals(12, index);
    }

}
