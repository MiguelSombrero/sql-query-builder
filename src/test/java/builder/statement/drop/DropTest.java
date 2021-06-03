package builder.statement.drop;

import builder.statement.create.table.column.DataType;
import query.QueryFactory;
import query.ddl.CreateQuery;
import query.Query;
import query.ddl.DDLQuery;
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

        DDLQuery query = queryFactory
                .drop()
                .table("test_table")
                .build();

        query.execute();

        assertEquals("DROP TABLE test_table", query.toString());

    }

    @Test
    public void testDropDatabase() throws SQLException {
        /*DDLQuery createDatabase = queryFactory
                .create()
                .database("test_db")
                .build();

        createDatabase.execute();*/

        DDLQuery query = queryFactory
                .drop()
                .database("test_db")
                .build();

        // DROP DATABASE not supported in H2?
        //query.execute();

        assertEquals("DROP DATABASE test_db", query.toString());
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
