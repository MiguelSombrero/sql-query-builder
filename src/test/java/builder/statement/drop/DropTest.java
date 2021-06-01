package builder.statement.drop;

import builder.statement.create.table.column.DataType;
import factory.QueryFactory;
import query.DDLQuery;
import query.Query;
import testutils.DatabaseConnection;
import testutils.DatabaseTestBaseClass;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static junit.framework.Assert.assertEquals;

public class DropTest extends DatabaseTestBaseClass {
    private QueryFactory queryFactory;

    @Before
    public void setUp() {
        initializeDatabase();
        queryFactory = new QueryFactory(DatabaseConnection.getDataSource());
    }

    @Test
    public void testDropTable() throws SQLException {
        DDLQuery createTable = queryFactory
                .create()
                .table("test_table")
                .column("id").type(DataType.INT)
                .build();

        createTable.execute();

        Query query = queryFactory
                .drop()
                .table("test_table")
                .build();

        assertEquals("DROP TABLE test_table", query.toString());

    }

    @Test
    public void testDropDatabase() {
        /*String createDatabase = QueryFactory
                .create()
                .database("test_db")
                .build();

        execute(createDatabase);
*/
        Query query = queryFactory
                .drop()
                .database("test_db")
                .build();

        // command not supported in H2?
        assertEquals("DROP DATABASE test_db", query.toString());
        //assertThatQueryIsValidSQL(query);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDropTableWithSQLInjection() {
        queryFactory
                .drop()
                .table(";DROP")
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDropDatabaseWithSQLInjection() {
        queryFactory
                .drop()
                .database(";DROP")
                .build();
    }
}
