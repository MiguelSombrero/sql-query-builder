package builder.statement.delete;

import testutils.DatabaseTestBaseClass;
import factory.QueryFactory;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static factory.WhereClauseFactory.valueOf;
import static org.junit.Assert.assertEquals;

public class DeleteTest extends DatabaseTestBaseClass {

    @Before
    public void setUp() {
        initializeDatabase();
    }

    @Test
    public void testDeleteFromOneTable() throws SQLException {
        String query = QueryFactory
                .deleteFrom()
                    .table("address")
                .build();

        assertEquals("DELETE FROM address", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testDeleteWithCondition() throws SQLException {
        String query = QueryFactory
                .deleteFrom()
                    .table("address")
                .where(valueOf("person_id").equals(1))
                .build();

        assertEquals("DELETE FROM address WHERE person_id = 1", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testDeleteWithMultipleCondition() throws SQLException {
        String query = QueryFactory
                .deleteFrom()
                    .table("address")
                .where(valueOf("person_id").equals(1)
                        .and(valueOf("city").equals("Oulu")))
                .build();

        assertEquals("DELETE FROM address WHERE person_id = 1 AND city = 'Oulu'", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeleteTableWithSQLInjection() {
        QueryFactory
                .deleteFrom()
                    .table("; DROP TABLE address")
                .build();
    }
}
