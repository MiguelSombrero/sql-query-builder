package builder.appender;

import clause.SQLClause;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StringAppenderTest {

    @Test
    public void testAppendWithValidInput() {
        SQLClause query = new SQLClause(new StringBuilder());
        StringAppender.validateAndAppend(query, "miika");
        assertEquals("miika", query.toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAppendWithInvalidInput() {
        SQLClause query = new SQLClause(new StringBuilder());
        StringAppender.validateAndAppend(query, "miika --");
    }

    @Test
    public void testAppendList() {
        SQLClause query = new SQLClause(new StringBuilder());
        StringAppender.validateAndAppendList(query, "miika", "liika", "siika", "riika");
        assertEquals("(miika, liika, siika, riika)", query.toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAppendListWithInvalidInput() {
        SQLClause query = new SQLClause(new StringBuilder());
        StringAppender.validateAndAppendList(query, "miika", "liika", "sii;DROP table person --ka", "riika");
    }

}
