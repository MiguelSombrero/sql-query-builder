package query;

import database.column.StringColumnValue;
import org.junit.Before;
import org.junit.Test;
import testutils.DatabaseConnection;
import testutils.DatabaseTestBaseClass;

import javax.sql.DataSource;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

public class InsertQueryTest extends DatabaseTestBaseClass {
    private DataSource dataSource;

    @Before
    public void setUp() {
        initializeDatabase();
        dataSource = DatabaseConnection.getH2DataSource();
    }

    @Test
    public void testExecuteAllFieldsInsert() throws SQLException {
        StringBuilder queryString = new StringBuilder("INSERT INTO all_types VALUES (100, 10, 8223372036854775806, 32.2, 15500.99, 1.6, '2021-03-01', '20:02:01', '2021-05-05 20:02:01', true, 10, 'T1000', 'Saab', 'Buy this', '64657374696e6720736f6d652076616c7565730a')");
        InsertQuery query = new InsertQuery(new SQLStatement(queryString), dataSource);

        int result = query.execute();
        assertEquals(100, result);

        assertThatQueryReturnsRows("SELECT * FROM all_types WHERE id = 100", 1);
        assertThatQueryReturnsRows("SELECT * FROM all_types WHERE rating = 10", 1);
        assertThatQueryReturnsRows("SELECT * FROM all_types WHERE hash = 8223372036854775806", 1);
        assertThatQueryReturnsRows("SELECT * FROM all_types WHERE age = 32.2", 1);
        assertThatQueryReturnsRows("SELECT * FROM all_types WHERE price = 15500.99", 1);
        assertThatQueryReturnsRows("SELECT * FROM all_types WHERE taxes = 1.6", 1);
        assertThatQueryReturnsRows("SELECT * FROM all_types WHERE newdate = '2021-03-01'", 1);
        assertThatQueryReturnsRows("SELECT * FROM all_types WHERE clock = '20:02:01'", 1);
        assertThatQueryReturnsRows("SELECT * FROM all_types WHERE created = '2021-05-05 20:02:01'", 1);
        assertThatQueryReturnsRows("SELECT * FROM all_types WHERE active = true", 2);
        assertThatQueryReturnsRows("SELECT * FROM all_types WHERE country = 10", 1);
        assertThatQueryReturnsRows("SELECT * FROM all_types WHERE model = 'T1000'", 1);
        assertThatQueryReturnsRows("SELECT * FROM all_types WHERE brand = 'Saab'", 1);
        assertThatQueryReturnsRows("SELECT * FROM all_types WHERE description = 'Buy this'", 1);
        assertThatQueryReturnsRows("SELECT * FROM all_types WHERE contract = '64657374696e6720736f6d652076616c7565730a'", 1);
    }

    @Test
    public void testExecuteParametrizedInsert() throws SQLException {
        StringBuilder queryString = new StringBuilder("INSERT INTO person (firstname, lastname) VALUES (");
        Statement statement = new SQLStatement(queryString);

        StringColumnValue param1 = new StringColumnValue("Lasse");
        StringColumnValue param2 = new StringColumnValue("Kukkonen");
        statement.addParam(param1);
        statement.append(", ");
        statement.addParam(param2);
        statement.append(")");

        InsertQuery query = new InsertQuery(statement, dataSource);

        int result = query.execute();
        assertEquals(4, result);

        assertThatQueryReturnsRows("SELECT * FROM person WHERE firstname = 'Lasse'", 1);
        assertThatQueryReturnsRows("SELECT * FROM person WHERE lastname = 'Kukkonen'", 1);
    }
}
