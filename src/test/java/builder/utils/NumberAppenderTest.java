package builder.utils;

import builder.Query;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NumberAppenderTest {

    @Test
    public void testAppendListOfIntegerValues() {
        Query query = new Query(new StringBuilder());
        NumberAppender numberAppender = new NumberAppender(query);
        numberAppender.appendListOfValues(0, 1, 2, 3, 4);
        assertEquals("(0, 1, 2, 3, 4)", query.build());
    }

    @Test
    public void testAppendListOfDoubleValues() {
        Query query = new Query(new StringBuilder());
        NumberAppender numberAppender = new NumberAppender(query);
        numberAppender.appendListOfValues(0.0, 1.1, 2.2, 3.3, 4.4);
        assertEquals("(0.0, 1.1, 2.2, 3.3, 4.4)", query.build());
    }

}
