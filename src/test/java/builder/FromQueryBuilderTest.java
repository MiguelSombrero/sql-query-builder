package builder;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class FromQueryBuilderTest {
    SelectQueryBuilder builder;

    @Before
    public void setUp() {
        this.builder = QueryFactory.select();
    }

    @Test
    public void testSelectFromAs() {
        String query = builder
                .value("lastname")
                .value("age")
                .value("firstname")
                .from("persons")
                    .alias("p")
                .build();

        assertEquals("SELECT lastname, age, firstname FROM persons p", query);
    }
}
