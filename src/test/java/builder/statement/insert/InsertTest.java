package builder.statement.insert;

import database.DatabaseTestBaseClass;
import factory.QueryFactory;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

public class InsertTest extends DatabaseTestBaseClass {
    private FirstColumn column;

    @Before
    public void setUpQuery() {
        this.column = QueryFactory
                .insertInto()
                .table("person");
    }

    @Test
    public void testInsertOneValue() throws SQLException {
        String query = QueryFactory
                .insertInto()
                .table("person")
                .column("id").value(100)
                .build();

        assertEquals("INSERT INTO person (id) VALUES (100);", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testInsertMultipleValues() throws SQLException {
        String query = QueryFactory
                .insertInto()
                .table("person")
                .column("id").value(101)
                .column("birthdate").value("1980-04-12")
                .column("firstname").value("Miika")
                .column("lastname").value("Somero")
                .column("age").value(40)
                .build();

        assertEquals("INSERT INTO person (id, birthdate, firstname, lastname, age) VALUES (101, '1980-04-12', 'Miika', 'Somero', 40);", query);
        assertThatQueryIsValidSQL(query);
    }
}
