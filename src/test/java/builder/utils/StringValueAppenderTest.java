package builder.utils;

import query.SQLQuery;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StringValueAppenderTest {

    @Test
    public void testAppendStringValue() {
        SQLQuery SQLQuery = new SQLQuery(new StringBuilder());
        StringValueAppender stringValueAppender = new StringValueAppender(SQLQuery);
        stringValueAppender.appendStringValue("miika");
        assertEquals("'miika'", SQLQuery.toString());
    }

    @Test
    public void testAppendStringValueQuestionMark() {
        SQLQuery SQLQuery = new SQLQuery(new StringBuilder());
        StringValueAppender stringValueAppender = new StringValueAppender(SQLQuery);
        stringValueAppender.appendStringValue("?");
        assertEquals("?", SQLQuery.toString());
    }

    @Test
    public void testAppendListOfStringValues() {
        SQLQuery SQLQuery = new SQLQuery(new StringBuilder());
        StringValueAppender stringValueAppender = new StringValueAppender(SQLQuery);
        stringValueAppender.appendListOfValues("miika", "liika", "siika", "riika");
        assertEquals("('miika', 'liika', 'siika', 'riika')", SQLQuery.toString());
    }
}
