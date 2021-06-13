package builder.query.create;

import builder.query.QueryFactory;
import query.ddl.CreateQuery;
import testutils.DatabaseConnection;
import testutils.DatabaseTestBaseClass;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;


public class CreateTest extends DatabaseTestBaseClass {
    private QueryFactory queryFactory;

    @Before
    public void setUp() throws SQLException {
        initializeDatabase();
        queryFactory = new QueryFactory(DatabaseConnection.getDataSource());
    }

    @Test
    public void testCreateDatabase() {
        CreateQuery query = queryFactory.create()
                .database("test_db")
                .build();

        assertEquals("CREATE DATABASE test_db", query.toString());
    }

    @Test
    public void testCreateIndex() throws SQLException {
        CreateQuery query = queryFactory.create()
                .index("person_index")
                .on("person")
                .columns("id", "firstname", "lastname")
                .build();

        assertEquals("CREATE INDEX person_index ON person (id, firstname, lastname)", query.toString());

        query.execute();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateDatabaseWithSQLInjection() {
         queryFactory.create()
                .database(";DROP")
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateIndexWithSQLInjection() {
        queryFactory.create()
                .index(";DROP")
                .on("person")
                .columns("id", "firstname", "lastname")
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateIndexOnWithSQLInjection()  {
        queryFactory.create()
                .index("person_index")
                .on(";DROP")
                .columns("id", "firstname", "lastname")
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateIndexOnColumnsWithSQLInjection()  {
        queryFactory.create()
                .index("person_index")
                .on("person")
                .columns("id", ";DROP", "lastname")
                .build();
    }
}
