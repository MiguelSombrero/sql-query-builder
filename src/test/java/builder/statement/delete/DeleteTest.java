package builder.statement.delete;

import database.DatabaseTestBaseClass;
import factory.QueryFactory;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

public class DeleteTest extends DatabaseTestBaseClass {

    @Test
    public void testDeleteFromOneTable() throws SQLException {
        String query = QueryFactory
                .deleteFrom()
                    .table("address")
                .build();

        assertEquals("DELETE FROM address;", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testDeleteWithCondition() throws SQLException {
        String query = QueryFactory
                .deleteFrom()
                .table("address")
                .where("person_id").equals(1)
                .build();

        assertEquals("DELETE FROM address WHERE person_id = 1;", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testDeleteWithMultipleCondition() throws SQLException {
        String query = QueryFactory
                .deleteFrom()
                .table("address")
                .where("person_id").equals(1)
                .and("city").equals("Oulu")
                .build();

        assertEquals("DELETE FROM address WHERE person_id = 1 AND city = 'Oulu';", query);
        assertThatQueryIsValidSQL(query);
    }
}
