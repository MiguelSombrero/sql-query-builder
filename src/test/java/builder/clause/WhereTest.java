package builder.clause;

import factory.QueryFactory;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class WhereTest {

    @Test
    public void testWhereAndCondition() {
        String query = QueryFactory
                .select()
                .value("lastname")
                .value("firstname")
                .from("persons")
                .where("age").greaterThan(18)
                .and("birthdate").greaterThan("2020-02-28T21:00:00.000")
                .build();

        assertEquals("SELECT lastname, firstname FROM persons WHERE age > 18 AND birthdate > '2020-02-28T21:00:00.000';", query);
    }


}
