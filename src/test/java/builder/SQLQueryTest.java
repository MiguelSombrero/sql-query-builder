package builder;

import org.junit.Test;
import query.SQLQuery;

import static org.junit.Assert.assertEquals;

public class SQLQueryTest {

    @Test
    public void testAppendString() {
        SQLQuery query = new SQLQuery(new StringBuilder());
        query.append("miika");
        query.append(" ");
        query.append("Somero");
        assertEquals("miika Somero", query.toString());
    }

    @Test
    public void testAppendInteger() {
        SQLQuery query = new SQLQuery(new StringBuilder());
        query.append(0);
        query.append(1);
        query.append(2);
        assertEquals("012", query.toString());
    }

    @Test
    public void testAppendDouble() {
        SQLQuery query = new SQLQuery(new StringBuilder());
        query.append(0.0);
        query.append(1.1);
        query.append(2.2);
        assertEquals("0.01.12.2", query.toString());
    }

    @Test
    public void testInsert() {
        SQLQuery query = new SQLQuery(new StringBuilder());
        query.insert(0, "somero");
        query.insert(0, "miika ");
        query.insert(6, "testaaja ");
        assertEquals("miika testaaja somero", query.toString());
    }
}
