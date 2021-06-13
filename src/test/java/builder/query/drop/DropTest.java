package builder.query.drop;

import builder.query.SQLQueryBuilder;
import builder.query.create.table.column.DataType;
import query.ddl.CreateQuery;
import query.ddl.DropQuery;
import testutils.DatabaseConnection;
import testutils.DatabaseTestBaseClass;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

public class DropTest extends DatabaseTestBaseClass {
    private SQLQueryBuilder SQLQueryBuilder;

    @Before
    public void setUp() throws SQLException {
        initializeDatabase();
        SQLQueryBuilder = new SQLQueryBuilder(DatabaseConnection.getDataSource());
    }

    @Test
    public void testDropTable() throws SQLException {
        CreateQuery createTable = SQLQueryBuilder
                .create()
                .table("test_table")
                .column("id").type(DataType.INT)
                .build();

        createTable.execute();

        DropQuery query = SQLQueryBuilder
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

        DropQuery query = SQLQueryBuilder
                .drop()
                .database("test_db")
                .build();

        // DROP DATABASE not supported in H2?
        //query.execute();

        assertEquals("DROP DATABASE test_db", query.toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDropTableWithSQLInjection() {
        SQLQueryBuilder
                .drop()
                .table(";DROP")
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDropDatabaseWithSQLInjection() {
        SQLQueryBuilder
                .drop()
                .database(";DROP")
                .build();
    }
}
