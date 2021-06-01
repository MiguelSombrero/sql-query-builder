package builder.utils;

import query.SQLQuery;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StringAppenderTest {

    @Test
    public void testAppendList() {
        SQLQuery query = new SQLQuery(new StringBuilder());
        StringAppender stringAppender = new StringAppender(query);
        stringAppender.validateAndAppendList("miika", "liika", "siika", "riika");
        assertEquals("(miika, liika, siika, riika)", query.toString());
    }
}
