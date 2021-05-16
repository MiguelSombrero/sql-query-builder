package builder.statement.drop;

import builder.statement.create.table.column.DataType;
import database.DatabaseTestBaseClass;
import factory.QueryFactory;
import org.junit.Before;
import org.junit.Test;

import javax.xml.bind.ValidationException;
import java.sql.SQLException;

import static factory.QueryFactory.create;
import static junit.framework.Assert.assertEquals;

public class DropTest extends DatabaseTestBaseClass {

    @Before
    public void setUp() {
        initializeDatabase();
    }

    @Test
    public void testDropTable() throws SQLException, ValidationException {
        String createTable = QueryFactory
                .create()
                .table("test_table")
                .column("id").type(DataType.INT)
                .build();

        execute(createTable);

        String query = QueryFactory
                .drop()
                .table("test_table")
                .build();

        assertEquals("DROP TABLE test_table", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testDropDatabase() throws SQLException, ValidationException {
        /*String createDatabase = QueryFactory
                .create()
                .database("test_db")
                .build();

        execute(createDatabase);
*/
        String query = QueryFactory
                .drop()
                .database("test_db")
                .build();

        // command not supported in H2?
        assertEquals("DROP DATABASE test_db", query);
        //assertThatQueryIsValidSQL(query);
    }

    @Test(expected = ValidationException.class)
    public void testDropTableWithSQLInjection() throws ValidationException {
        QueryFactory
                .drop()
                .table(";DROP")
                .build();
    }

    @Test(expected = ValidationException.class)
    public void testDropDatabaseWithSQLInjection() throws ValidationException {
        QueryFactory
                .drop()
                .database(";DROP")
                .build();
    }
}
