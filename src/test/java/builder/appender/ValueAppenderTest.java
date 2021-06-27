package builder.appender;

import query.SQLStatement;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ValueAppenderTest {

    @Test
    public void testAppendStringParam() {
        SQLStatement query = new SQLStatement(new StringBuilder());
        ValueAppender.appendStringParam(query, "miika");
        assertEquals("'miika'", query.toString());
    }

    @Test
    public void testAppendIntParam() {
        SQLStatement query = new SQLStatement(new StringBuilder());
        ValueAppender.appendIntParam(query, 1);
        assertEquals("1", query.toString());
    }

    @Test
    public void testAppendShortParam() {
        SQLStatement query = new SQLStatement(new StringBuilder());
        ValueAppender.appendShortParam(query, Short.valueOf("1"));
        assertEquals("1", query.toString());
    }

    @Test
    public void testAppendDoubleParam() {
        SQLStatement query = new SQLStatement(new StringBuilder());
        ValueAppender.appendDoubleParam(query, 1.1);
        assertEquals("1.1", query.toString());
    }

}
