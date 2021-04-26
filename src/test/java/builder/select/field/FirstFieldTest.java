package builder.select.field;

import database.DatabaseTestBaseClass;
import factory.QueryFactory;
import org.junit.Test;

import java.sql.SQLException;

import static junit.framework.Assert.assertEquals;

public class FirstFieldTest extends DatabaseTestBaseClass {

    @Test
    public void testSelectFromTable() throws SQLException {
        String query = QueryFactory
                .select()
                    .field("lastname")
                    .field("age")
                    .field("firstname")
                .from("person")
                .build();

        assertEquals("SELECT lastname, age, firstname FROM person;", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testSelectTopFromTable() throws SQLException {
        String query = QueryFactory
                .selectTop(100)
                .field("lastname")
                .field("age")
                .field("firstname")
                .from("person")
                .build();

        assertEquals("SELECT TOP 100 lastname, age, firstname FROM person;", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testSelectDistinctFromTable() throws SQLException {
        String query = QueryFactory
                .selectDistinct()
                    .field("lastname")
                    .field("age")
                    .field("firstname")
                .from("person")
                .build();

        assertEquals("SELECT DISTINCT lastname, age, firstname FROM person;", query);
        assertThatQueryIsValidSQL(query);
    }
}
