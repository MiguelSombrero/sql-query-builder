package builder.query.create;

import builder.query.SQLQueryBuilder;
import org.junit.Before;
import org.junit.Test;
import testutils.DatabaseConnection;
import testutils.DatabaseTestBaseClass;

import java.sql.SQLException;

public class CreateValidationTest extends DatabaseTestBaseClass {
    private SQLQueryBuilder sqlQueryBuilder;

    @Before
    public void setUp() throws SQLException {
        initializeDatabase();
        sqlQueryBuilder = new SQLQueryBuilder(DatabaseConnection.getH2DataSource());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateDatabaseWithSQLInjection() {
         sqlQueryBuilder.create()
                .database(";DROP")
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateIndexWithSQLInjection() {
        sqlQueryBuilder.create()
                .index(";DROP")
                .on("person")
                .columns("id", "firstname", "lastname")
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateIndexOnWithSQLInjection()  {
        sqlQueryBuilder.create()
                .index("person_index")
                .on(";DROP")
                .columns("id", "firstname", "lastname")
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateIndexOnColumnsWithSQLInjection()  {
        sqlQueryBuilder.create()
                .index("person_index")
                .on("person")
                .columns("id", ";DROP", "lastname")
                .build();
    }
}
