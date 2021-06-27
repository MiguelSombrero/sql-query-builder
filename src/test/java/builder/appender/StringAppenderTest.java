package builder.appender;

import query.SQLStatement;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StringAppenderTest {

    @Test
    public void testAppendWithValidInput() {
        SQLStatement query = new SQLStatement(new StringBuilder());
        StringAppender.validateAndAppend(query, "miika");
        assertEquals("miika", query.toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAppendWithInvalidInput() {
        SQLStatement query = new SQLStatement(new StringBuilder());
        StringAppender.validateAndAppend(query, "miika --");
    }

    @Test
    public void testAppendList() {
        SQLStatement query = new SQLStatement(new StringBuilder());
        StringAppender.validateAndAppendList(query, "miika", "liika", "siika", "riika");
        assertEquals("(miika, liika, siika, riika)", query.toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAppendListWithInvalidInput() {
        SQLStatement query = new SQLStatement(new StringBuilder());
        StringAppender.validateAndAppendList(query, "miika", "liika", "sii;DROP table person --ka", "riika");
    }

}
