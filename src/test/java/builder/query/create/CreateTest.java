package builder.query.create;

import builder.query.SQLQueryBuilder;
import query.ddl.CreateQuery;
import testutils.DatabaseConnection;
import testutils.DatabaseTestBaseClass;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;


public class CreateTest extends DatabaseTestBaseClass {
    private SQLQueryBuilder SQLQueryBuilder;

    @Before
    public void setUp() throws SQLException {
        initializeDatabase();
        SQLQueryBuilder = new SQLQueryBuilder(DatabaseConnection.getDataSource());
    }

    @Test
    public void testCreateDatabase() {
        CreateQuery query = SQLQueryBuilder.create()
                .database("test_db")
                .build();

        assertEquals("CREATE DATABASE test_db", query.toString());
    }

    @Test
    public void testCreateIndex() throws SQLException {
        CreateQuery query = SQLQueryBuilder.create()
                .index("person_index")
                .on("person")
                .columns("id", "firstname", "lastname")
                .build();

        assertEquals("CREATE INDEX person_index ON person (id, firstname, lastname)", query.toString());

        query.execute();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateDatabaseWithSQLInjection() {
         SQLQueryBuilder.create()
                .database(";DROP")
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateIndexWithSQLInjection() {
        SQLQueryBuilder.create()
                .index(";DROP")
                .on("person")
                .columns("id", "firstname", "lastname")
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateIndexOnWithSQLInjection()  {
        SQLQueryBuilder.create()
                .index("person_index")
                .on(";DROP")
                .columns("id", "firstname", "lastname")
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateIndexOnColumnsWithSQLInjection()  {
        SQLQueryBuilder.create()
                .index("person_index")
                .on("person")
                .columns("id", ";DROP", "lastname")
                .build();
    }
}
