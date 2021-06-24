package builder.query.drop;

import builder.query.SQLQueryBuilder;
import query.DropQuery;
import testutils.DatabaseConnection;
import testutils.DatabaseTestBaseClass;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

public class DropTest extends DatabaseTestBaseClass {
    private SQLQueryBuilder sqlQueryBuilder;

    @Before
    public void setUp() throws SQLException {
        initializeDatabase();
        sqlQueryBuilder = new SQLQueryBuilder(DatabaseConnection.getDataSource());
    }

    @Test
    public void testDropTable() throws SQLException {
        DropQuery query = sqlQueryBuilder
                .drop()
                .table()
                .name("course")
                .build();

        query.execute();

        assertEquals("DROP TABLE course", query.toString());
    }

    @Test
    public void testDropTableIfExists() throws SQLException {
        DropQuery query = sqlQueryBuilder
                .drop()
                .table()
                .ifExists()
                .name("course")
                .build();

        query.execute();

        assertEquals("DROP TABLE IF EXISTS course", query.toString());
    }

    @Test
    public void testDropDatabase() throws SQLException {
        /*DDLQuery createDatabase = queryFactory
                .create()
                .database("test_db")
                .build();

        createDatabase.execute();*/

        DropQuery query = sqlQueryBuilder
                .drop()
                .database()
                .name("test_db")
                .build();

        // DROP DATABASE not supported in H2?
        //query.execute();

        assertEquals("DROP DATABASE test_db", query.toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDropTableWithSQLInjection() {
        sqlQueryBuilder
                .drop()
                .table()
                .name(";DROP")
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDropDatabaseWithSQLInjection() {
        sqlQueryBuilder
                .drop()
                .database()
                .name(";DROP")
                .build();
    }
}
