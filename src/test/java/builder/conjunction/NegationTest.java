package builder.conjunction;

import builder.table.Table;
import factory.QueryFactory;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class NegationTest {
    private Table table;

    @Before
    public void setUp() {
        this.table = QueryFactory
                .select()
                .field("firstname")
                .from("persons");
    }

    @Test
    public void testWhereNotCondition() {
        String query = this.table
                .where("age").not().greaterThan(18)
                .build();

        assertEquals("SELECT firstname FROM persons WHERE NOT age > 18;", query);
    }

    @Test
    public void testWhereNotAndNotConditions() {
        String query = this.table
                .where("age").not().greaterThan(18)
                .and("name").not().equals("Miika")
                .build();

        assertEquals("SELECT firstname FROM persons WHERE NOT age > 18 AND NOT name = 'Miika';", query);
    }

    @Test
    public void testThatLastIndexOfReturnsIndexOfBlanksCorrectly() {
        StringBuilder builder = new StringBuilder("person WHERE NOT");
        int index = builder.lastIndexOf(" ");
        assertEquals(12, index);
    }

}
