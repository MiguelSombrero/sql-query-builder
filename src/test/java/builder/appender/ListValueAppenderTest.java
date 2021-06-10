package builder.appender;

import query.SQLQuery;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ListValueAppenderTest {

    @Test
    public void testAppendListOfIntegerValues() {
        SQLQuery query = new SQLQuery(new StringBuilder());
        ListValueAppender.appendListOfValues(query, 0, 1, 2, 3, 4);
        assertEquals("(0, 1, 2, 3, 4)", query.toString());
    }

    @Test
    public void testAppendListOfDoubleValues() {
        SQLQuery query = new SQLQuery(new StringBuilder());
        ListValueAppender.appendListOfValues(query, 0.0, 1.1, 2.2, 3.3, 4.4);
        assertEquals("(0.0, 1.1, 2.2, 3.3, 4.4)", query.toString());
    }

}
