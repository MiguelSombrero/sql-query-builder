package builder.appender;

import query.SQLStatement;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ListValueAppenderTest {

    @Test
    public void testAppendListOfStringValues() {
        SQLStatement query = new SQLStatement(new StringBuilder());
        ListValueAppender.appendListOfStringParams(query, "miika", "liika", "siika", "riika");
        assertEquals("('miika', 'liika', 'siika', 'riika')", query.toString());
    }

    @Test
    public void testAppendListOfDateValues() {
        SQLStatement query = new SQLStatement(new StringBuilder());
        ListValueAppender.appendListOfDateParams(query, "1981-01-01", "1982-02-02", "1983-03-03", "1984-04-04");
        assertEquals("('1981-01-01', '1982-02-02', '1983-03-03', '1984-04-04')", query.toString());
    }

    @Test
    public void testAppendListOfTimestampValues() {
        SQLStatement query = new SQLStatement(new StringBuilder());
        ListValueAppender.appendListOfTimestampParams(query, "1981-01-01 21:01:01", "1982-02-02 21:02:02", "1983-03-03 21:03:03", "1984-04-04 21:04:04");
        assertEquals("('1981-01-01 21:01:01.0', '1982-02-02 21:02:02.0', '1983-03-03 21:03:03.0', '1984-04-04 21:04:04.0')", query.toString());
    }

    @Test
    public void testAppendListOfIntegerValues() {
        SQLStatement query = new SQLStatement(new StringBuilder());
        ListValueAppender.appendListOfIntParams(query, 0, 1, 2, 3, 4);
        assertEquals("(0, 1, 2, 3, 4)", query.toString());
    }

    @Test
    public void testAppendListOfLongValues() {
        SQLStatement query = new SQLStatement(new StringBuilder());
        ListValueAppender.appendListOfLongParams(query, 9223372036854775801L, 9223372036854775802L, 9223372036854775803L, 9223372036854775804L);
        assertEquals("(9223372036854775801, 9223372036854775802, 9223372036854775803, 9223372036854775804)", query.toString());
    }

    @Test
    public void testAppendListOfDoubleValues() {
        SQLStatement query = new SQLStatement(new StringBuilder());
        ListValueAppender.appendListOfDoubleParams(query, 0.0, 1.1, 2.2, 3.3, 4.4);
        assertEquals("(0.0, 1.1, 2.2, 3.3, 4.4)", query.toString());
    }



}
