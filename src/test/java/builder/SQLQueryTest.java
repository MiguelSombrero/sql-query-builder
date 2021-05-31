package builder;

import org.junit.Test;
import query.SQLQuery;

import static org.junit.Assert.assertEquals;

public class SQLQueryTest {

    @Test
    public void testAppendString() {
        SQLQuery SQLQuery = new SQLQuery(new StringBuilder());
        SQLQuery.append("miika");
        SQLQuery.append(" ");
        SQLQuery.append("Somero");
        assertEquals("miika Somero", SQLQuery.build());
    }

    @Test
    public void testAppendInteger() {
        SQLQuery SQLQuery = new SQLQuery(new StringBuilder());
        SQLQuery.append(0);
        SQLQuery.append(1);
        SQLQuery.append(2);
        assertEquals("012", SQLQuery.build());
    }

    @Test
    public void testAppendDouble() {
        SQLQuery SQLQuery = new SQLQuery(new StringBuilder());
        SQLQuery.append(0.0);
        SQLQuery.append(1.1);
        SQLQuery.append(2.2);
        assertEquals("0.01.12.2", SQLQuery.build());
    }

    @Test
    public void testInsert() {
        SQLQuery SQLQuery = new SQLQuery(new StringBuilder());
        SQLQuery.insert(0, "somero");
        SQLQuery.insert(0, "miika ");
        SQLQuery.insert(6, "testaaja ");
        assertEquals("miika testaaja somero", SQLQuery.build());
    }
}
