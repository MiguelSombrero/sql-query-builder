package builder.field;

import database.DatabaseTestBaseClass;
import factory.QueryFactory;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.SQLException;

import static junit.framework.Assert.assertEquals;

public class FirstFieldTest extends DatabaseTestBaseClass {

    @BeforeClass
    public static void setUp() {
        createTestDatabase();
        insertDataToTestDatabase();
    }

    @Test
    public void testSelectFromTable() throws SQLException {
        String query = QueryFactory
                .select()
                    .field("lastname")
                    .field("age")
                    .field("firstname")
                .from("persons")
                .build();

        assertEquals("SELECT lastname, age, firstname FROM persons;", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testSelectDistinctFromTable() throws SQLException {
        String query = QueryFactory
                .selectDistinct()
                    .field("lastname")
                    .field("age")
                    .field("firstname")
                .from("persons")
                .build();

        assertEquals("SELECT DISTINCT lastname, age, firstname FROM persons;", query);
        assertThatQueryIsValidSQL(query);
    }
}
