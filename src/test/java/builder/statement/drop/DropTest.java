package builder.statement.drop;

import builder.statement.create.DataType;
import database.DatabaseTestBaseClass;
import factory.QueryFactory;
import org.junit.Test;

import java.sql.SQLException;

import static junit.framework.Assert.assertEquals;

public class DropTest extends DatabaseTestBaseClass {

    @Test
    public void testDropTable() throws SQLException {
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
    public void testDropDatabase() throws SQLException {
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
}
