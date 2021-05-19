package builder.query;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class QueryAppenderTest {

    @Test
    public void testAppendStringValue() {
        Query query = new Query(new StringBuilder());
        QueryAppender.appendStringValue(query, "miika");
        assertEquals("'miika'", query.build());
    }

    @Test
    public void testAppendStringValueQuestionMark() {
        Query query = new Query(new StringBuilder());
        QueryAppender.appendStringValue(query, "?");
        assertEquals("?", query.build());
    }

    @Test
    public void testAppendList() {
        Query query = new Query(new StringBuilder());
        QueryAppender.appendList(query, "miika", "liika", "siika", "riika");
        assertEquals("(miika, liika, siika, riika)", query.build());
    }

    @Test
    public void testAppendListOfStringValues() {
        Query query = new Query(new StringBuilder());
        QueryAppender.appendListOfValues(query, "miika", "liika", "siika", "riika");
        assertEquals("('miika', 'liika', 'siika', 'riika')", query.build());
    }

    @Test
    public void testAppendListOfIntegerValues() {
        Query query = new Query(new StringBuilder());
        QueryAppender.appendListOfValues(query, 0, 1, 2, 3, 4);
        assertEquals("(0, 1, 2, 3, 4)", query.build());
    }

    @Test
    public void testAppendListOfDoubleValues() {
        Query query = new Query(new StringBuilder());
        QueryAppender.appendListOfValues(query, 0.0, 1.1, 2.2, 3.3, 4.4);
        assertEquals("(0.0, 1.1, 2.2, 3.3, 4.4)", query.build());
    }

}
