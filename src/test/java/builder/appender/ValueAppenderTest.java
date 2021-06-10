package builder.appender;

import query.SQLQuery;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ValueAppenderTest {

    @Test
    public void testAppendStringParam() {
        SQLQuery query = new SQLQuery(new StringBuilder());
        ValueAppender.appendStringParam(query, "miika");
        assertEquals("'miika'", query.toString());
    }

    @Test
    public void testAppendIntParam() {
        SQLQuery query = new SQLQuery(new StringBuilder());
        ValueAppender.appendIntParam(query, 1);
        assertEquals("1", query.toString());
    }

    @Test
    public void testAppendDoubleParam() {
        SQLQuery query = new SQLQuery(new StringBuilder());
        ValueAppender.appendDoubleParam(query, 1.1);
        assertEquals("1.1", query.toString());
    }

}
