package builder.appender;

import query.SQLClause;
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
    public void testAppendListOfDateValues() {
        SQLClause query = new SQLClause(new StringBuilder());
        ListValueAppender.appendListOfDateParams(query, "1981-01-01", "1982-02-02", "1983-03-03", "1984-04-04");
        assertEquals("('1981-01-01', '1982-02-02', '1983-03-03', '1984-04-04')", query.toString());
    }

    @Test
    public void testAppendListOfDateTimeValues() {
        SQLClause query = new SQLClause(new StringBuilder());
        ListValueAppender.appendListOfDateTimeParams(query, "1981-01-01 21:01:01", "1982-02-02 21:02:02", "1983-03-03 21:03:03", "1984-04-04 21:04:04");
        assertEquals("('1981-01-01T21:01:01', '1982-02-02T21:02:02', '1983-03-03T21:03:03', '1984-04-04T21:04:04')", query.toString());
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
