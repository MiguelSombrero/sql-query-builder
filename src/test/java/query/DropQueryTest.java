package query;

import org.junit.Before;
import org.junit.Test;
import testutils.DatabaseConnection;
import testutils.DatabaseTestBaseClass;

import javax.sql.DataSource;
import java.sql.SQLException;

public class DropQueryTest extends DatabaseTestBaseClass {
    private DataSource dataSource;

    @Before
    public void setUp() {
        initializeDatabase();
        dataSource = DatabaseConnection.getH2DataSource();
    }

    @Test(expected = SQLException.class)
    public void testExecuteAllFieldsCreateTable() throws SQLException {
        StringBuilder queryString = new StringBuilder("DROP TABLE course");
        DropQuery query = new DropQuery(new SQLStatement(queryString), dataSource);

        query.execute();

        assertThatQueryReturnsRows("SELECT * FROM course", 0);
    }
}
