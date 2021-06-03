package builder.statement.create;

import query.QueryFactory;
import query.Query;
import query.ddl.DDLQuery;
import testutils.DatabaseConnection;
import testutils.DatabaseTestBaseClass;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;


public class CreateTest extends DatabaseTestBaseClass {
    private QueryFactory queryFactory;

    @Before
    public void setUp() {
        initializeDatabase();
        queryFactory = new QueryFactory(DatabaseConnection.getDataSource());
    }

    @Test
    public void testCreateDatabase() {
        DDLQuery query = queryFactory.create()
                .database("test_db")
                .build();

        assertEquals("CREATE DATABASE test_db", query.toString());
    }

    @Test
    public void testCreateIndex() throws SQLException {
        DDLQuery query = queryFactory.create()
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
