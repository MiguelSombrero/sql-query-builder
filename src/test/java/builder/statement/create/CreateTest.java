package builder.statement.create;

import builder.statement.create.table.column.DataType;
import database.DatabaseTestBaseClass;
import org.junit.Before;
import org.junit.Test;

import javax.xml.bind.ValidationException;
import java.sql.SQLException;

import static factory.QueryFactory.create;
import static junit.framework.Assert.assertEquals;

public class CreateTest extends DatabaseTestBaseClass {

    @Before
    public void setUp() {
        initializeDatabase();
    }

    @Test
    public void testCreateDatabase() throws SQLException, ValidationException {
        String query = create()
                .database("test_db")
                .build();

        assertEquals("CREATE DATABASE test_db", query);
        // command not supported in H2?
        // assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testCreateIndex() throws SQLException, ValidationException {
        String query = create()
                .index("person_index")
                .on("person")
                .columns("id", "firstname", "lastname")
                .build();

        assertEquals("CREATE INDEX person_index ON person (id, firstname, lastname)", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test(expected = ValidationException.class)
    public void testCreateDatabaseWithSQLInjection() throws ValidationException {
         create()
                .database(";DROP")
                .build();
    }

    @Test(expected = ValidationException.class)
    public void testCreateIndexWithSQLInjection() throws ValidationException {
        create()
                .index(";DROP")
                .on("person")
                .columns("id", "firstname", "lastname")
                .build();
    }

    @Test(expected = ValidationException.class)
    public void testCreateIndexOnWithSQLInjection() throws ValidationException {
        create()
                .index("person_index")
                .on(";DROP")
                .columns("id", "firstname", "lastname")
                .build();
    }

    @Test(expected = ValidationException.class)
    public void testCreateIndexOnColumnsWithSQLInjection() throws ValidationException {
        create()
                .index("person_index")
                .on("person")
                .columns("id", ";DROP", "lastname")
                .build();
    }
}
