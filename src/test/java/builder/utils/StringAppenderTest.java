package builder.utils;

import builder.Query;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StringAppenderTest {

    @Test
    public void testAppendList() {
        Query query = new Query(new StringBuilder());
        StringAppender stringAppender = new StringAppender(query);
        stringAppender.validateAndAppendList("miika", "liika", "siika", "riika");
        assertEquals("(miika, liika, siika, riika)", query.build());
    }
}
