package builder.appender;

import query.SQLQuery;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ValueAppenderTest {

    @Test
    public void testAppendStringValue() {
        SQLQuery query = new SQLQuery(new StringBuilder());
        ValueAppender.appendStringParam(query, "miika");
        assertEquals("'miika'", query.toString());
    }

}
