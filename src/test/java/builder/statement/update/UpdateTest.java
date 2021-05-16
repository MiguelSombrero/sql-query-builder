package builder.statement.update;

import database.DatabaseTestBaseClass;
import factory.QueryFactory;
import org.junit.Before;
import org.junit.Test;

import javax.xml.bind.ValidationException;
import java.sql.SQLException;

import static factory.WhereClauseFactory.valueOf;
import static org.junit.Assert.assertEquals;

public class UpdateTest extends DatabaseTestBaseClass {

    @Before
    public void setUp() {
        initializeDatabase();
    }

    @Test
    public void testUpdateIntegerValue() throws SQLException, ValidationException {
        String query = QueryFactory
                .update()
                .table("person")
                    .column("age").value(50)
                .build();

        assertEquals("UPDATE person SET age = 50", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testUpdateStringValue() throws SQLException, ValidationException {
        String query = QueryFactory
                .update()
                .table("person")
                    .column("firstname").value("Miika")
                .build();

        assertEquals("UPDATE person SET firstname = 'Miika'", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testUpdateMultipleValues() throws SQLException, ValidationException {
        String query = QueryFactory
                .update()
                .table("person")
                    .column("firstname").value("Miika")
                    .column("lastname").value("Somero")
                    .column("age").value(50)
                .build();

        assertEquals("UPDATE person SET firstname = 'Miika', lastname = 'Somero', age = 50", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testUpdateWithCondition() throws SQLException, ValidationException {
        String query = QueryFactory
                .update()
                .table("person")
                    .column("age").value(50)
                .where(valueOf("id").equals(1)
                        .or(valueOf("id").equals(2)))
                .build();

        assertEquals("UPDATE person SET age = 50 WHERE id = 1 OR id = 2", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test(expected = ValidationException.class)
    public void testUpdateTableWithSQLInjection() throws ValidationException {
        QueryFactory
                .update()
                .table(";DROP")
                .column("firstname").value("Miika")
                .column("lastname").value("Somero")
                .column("age").value(50)
                .build();
    }

    @Test(expected = ValidationException.class)
    public void testUpdateColumnWithSQLInjection() throws ValidationException {
        QueryFactory
                .update()
                .table("person")
                .column("firstname").value("Miika")
                .column(";DROP").value("Somero")
                .column("age").value(50)
                .build();
    }

    @Test(expected = ValidationException.class)
    public void testUpdateValueWithSQLInjection() throws ValidationException {
        QueryFactory
                .update()
                .table("person")
                    .column("firstname").value("Miika")
                    .column("lastname").value(";DROP")
                    .column("age").value(50)
                .build();
    }
}
