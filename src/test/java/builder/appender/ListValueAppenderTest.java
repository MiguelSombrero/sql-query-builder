package builder.appender;

import clause.SQLClause;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ListValueAppenderTest {

    @Test
    public void testAppendListOfStringValues() {
        SQLClause query = new SQLClause(new StringBuilder());
        ListValueAppender.appendListOfStringParams(query, "miika", "liika", "siika", "riika");
        assertEquals("('miika', 'liika', 'siika', 'riika')", query.toString());
    }
    
    @Test
    public void testAppendListOfIntegerValues() {
        SQLClause query = new SQLClause(new StringBuilder());
        ListValueAppender.appendListOfIntParams(query, 0, 1, 2, 3, 4);
        assertEquals("(0, 1, 2, 3, 4)", query.toString());
    }

    @Test
    public void testAppendListOfLongValues() {
        SQLClause query = new SQLClause(new StringBuilder());
        ListValueAppender.appendListOfLongParams(query, 9223372036854775801L, 9223372036854775802L, 9223372036854775803L, 9223372036854775804L);
        assertEquals("(9223372036854775801, 9223372036854775802, 9223372036854775803, 9223372036854775804)", query.toString());
    }

    @Test
    public void testAppendListOfDoubleValues() {
        SQLClause query = new SQLClause(new StringBuilder());
        ListValueAppender.appendListOfDoubleParams(query, 0.0, 1.1, 2.2, 3.3, 4.4);
        assertEquals("(0.0, 1.1, 2.2, 3.3, 4.4)", query.toString());
    }



}
