package builder.statement.create;

import database.DatabaseTestBaseClass;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static factory.QueryFactory.create;
import static junit.framework.Assert.assertEquals;

public class CreateTest extends DatabaseTestBaseClass {

    @Before
    public void setUp() {
        initializeDatabase();
    }

    @Test
    public void testCreateDatabase() throws SQLException {
        String query = create()
                .database("test_db")
                .build();

        assertEquals("CREATE DATABASE test_db", query);
        // command not supported in H2?
        // assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testCreateIndex() throws SQLException {
        String query = create()
                .index("person_index")
                .on("person")
                .columns("id", "firstname", "lastname")
                .build();

        assertEquals("CREATE INDEX person_index ON person (id, firstname, lastname)", query);
        assertThatQueryIsValidSQL(query);
    }
}
