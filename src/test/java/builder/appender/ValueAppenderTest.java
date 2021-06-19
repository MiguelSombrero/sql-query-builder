package builder.appender;

import query.SQLClause;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ValueAppenderTest {

    @Test
    public void testAppendStringParam() {
        SQLClause query = new SQLClause(new StringBuilder());
        ValueAppender.appendStringParam(query, "miika");
        assertEquals("'miika'", query.toString());
    }

    @Test
    public void testAppendIntParam() {
        SQLClause query = new SQLClause(new StringBuilder());
        ValueAppender.appendIntParam(query, 1);
        assertEquals("1", query.toString());
    }

    @Test
    public void testAppendDoubleParam() {
        SQLClause query = new SQLClause(new StringBuilder());
        ValueAppender.appendDoubleParam(query, 1.1);
        assertEquals("1.1", query.toString());
    }

}
