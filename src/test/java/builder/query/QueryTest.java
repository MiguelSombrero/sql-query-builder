package builder.query;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class QueryTest {

    @Test
    public void testAppendString() {
        Query query = new Query(new StringBuilder());
        query.append("miika");
        query.append(" ");
        query.append("Somero");
        assertEquals("miika Somero", query.build());
    }

    @Test
    public void testAppendInteger() {
        Query query = new Query(new StringBuilder());
        query.append(0);
        query.append(1);
        query.append(2);
        assertEquals("012", query.build());
    }

    @Test
    public void testAppendDouble() {
        Query query = new Query(new StringBuilder());
        query.append(0.0);
        query.append(1.1);
        query.append(2.2);
        assertEquals("0.01.12.2", query.build());
    }

    @Test
    public void testInsert() {
        Query query = new Query(new StringBuilder());
        query.insert(0, "somero");
        query.insert(0, "miika ");
        query.insert(6, "testaaja ");
        assertEquals("miika testaaja somero", query.build());
    }
}
