package builder.appender;

import query.SQLQuery;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StringAppenderTest {

    @Test
    public void testAppendWithValidInput() {
        SQLQuery query = new SQLQuery(new StringBuilder());
        StringAppender.validateAndAppend(query, "miika");
        assertEquals("miika", query.toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAppendWithInvalidInput() {
        SQLQuery query = new SQLQuery(new StringBuilder());
        StringAppender.validateAndAppend(query, "miika --");
    }

    @Test
    public void testAppendList() {
        SQLQuery query = new SQLQuery(new StringBuilder());
        StringAppender.validateAndAppendList(query, "miika", "liika", "siika", "riika");
        assertEquals("(miika, liika, siika, riika)", query.toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAppendListWithInvalidInput() {
        SQLQuery query = new SQLQuery(new StringBuilder());
        StringAppender.validateAndAppendList(query, "miika", "liika", "sii;DROP table person --ka", "riika");
    }

}
