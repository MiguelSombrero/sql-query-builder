package builder.statement;

import builder.QueryFactory;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class FromStatementBuilderTest {

    @Test
    public void testSelectFromAs() {
        String query = QueryFactory
                .select()
                    .value("lastname")
                    .value("age")
                    .value("firstname")
                .from("persons")
                    .alias("p")
                .build();

        assertEquals("SELECT lastname, age, firstname FROM persons p;", query);
    }
}
