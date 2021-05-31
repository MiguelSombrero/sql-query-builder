package builder.utils;

import query.SQLQuery;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NumberAppenderTest {

    @Test
    public void testAppendListOfIntegerValues() {
        SQLQuery SQLQuery = new SQLQuery(new StringBuilder());
        NumberAppender numberAppender = new NumberAppender(SQLQuery);
        numberAppender.appendListOfValues(0, 1, 2, 3, 4);
        assertEquals("(0, 1, 2, 3, 4)", SQLQuery.build());
    }

    @Test
    public void testAppendListOfDoubleValues() {
        SQLQuery SQLQuery = new SQLQuery(new StringBuilder());
        NumberAppender numberAppender = new NumberAppender(SQLQuery);
        numberAppender.appendListOfValues(0.0, 1.1, 2.2, 3.3, 4.4);
        assertEquals("(0.0, 1.1, 2.2, 3.3, 4.4)", SQLQuery.build());
    }

}
