package builder.utils;

import builder.Query;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StringValueAppenderTest {

    @Test
    public void testAppendStringValue() {
        Query query = new Query(new StringBuilder());
        StringValueAppender stringValueAppender = new StringValueAppender(query);
        stringValueAppender.appendStringValue("miika");
        assertEquals("'miika'", query.build());
    }

    @Test
    public void testAppendStringValueQuestionMark() {
        Query query = new Query(new StringBuilder());
        StringValueAppender stringValueAppender = new StringValueAppender(query);
        stringValueAppender.appendStringValue("?");
        assertEquals("?", query.build());
    }

    @Test
    public void testAppendListOfStringValues() {
        Query query = new Query(new StringBuilder());
        StringValueAppender stringValueAppender = new StringValueAppender(query);
        stringValueAppender.appendListOfValues("miika", "liika", "siika", "riika");
        assertEquals("('miika', 'liika', 'siika', 'riika')", query.build());
    }
}
