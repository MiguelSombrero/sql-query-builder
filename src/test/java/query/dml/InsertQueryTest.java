package query.dml;

import database.column.StringColumnValue;
import database.row.Row;
import org.junit.Before;
import org.junit.Test;
import query.dql.SelectQuery;
import testutils.DatabaseConnection;
import testutils.DatabaseTestBaseClass;

import javax.sql.DataSource;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class InsertQueryTest extends DatabaseTestBaseClass {
    private DataSource dataSource;

    @Before
    public void setUp() {
        initializeDatabase();
        dataSource = DatabaseConnection.getDataSource();
    }

    @Test
    public void testExecuteAllFieldsInsert() throws SQLException {
        StringBuilder queryString = new StringBuilder("INSERT INTO all_types VALUES (100, 8223372036854775806, 32.2, '2021-03-01', '2021-04-04 21:02:01', '2021-05-05 20:02:01', true, 10, 'T1000', 'Saab', 'Great car', 'Buy this', '64657374696e6720736f6d652076616c7565730a')");
        InsertQuery query = new InsertQuery(queryString, dataSource);

        int result = query.execute();
        assertEquals(100, result);

        assertThatQueryReturnsRows("SELECT * FROM all_types WHERE id = 100", 1);
        assertThatQueryReturnsRows("SELECT * FROM all_types WHERE hash = 8223372036854775806", 1);
        assertThatQueryReturnsRows("SELECT * FROM all_types WHERE age = 32.2", 1);
        assertThatQueryReturnsRows("SELECT * FROM all_types WHERE newdate = '2021-03-01'", 1);
        assertThatQueryReturnsRows("SELECT * FROM all_types WHERE newdatetime = '2021-04-04 21:02:01'", 1);
        assertThatQueryReturnsRows("SELECT * FROM all_types WHERE created = '2021-05-05 20:02:01'", 1);
        assertThatQueryReturnsRows("SELECT * FROM all_types WHERE active = true", 2);
        assertThatQueryReturnsRows("SELECT * FROM all_types WHERE country = 10", 1);
        assertThatQueryReturnsRows("SELECT * FROM all_types WHERE model = 'T1000'", 1);
        assertThatQueryReturnsRows("SELECT * FROM all_types WHERE brand = 'Saab'", 1);
        assertThatQueryReturnsRows("SELECT * FROM all_types WHERE disclaimer = 'Great car'", 1);
        assertThatQueryReturnsRows("SELECT * FROM all_types WHERE description = 'Buy this'", 1);
        assertThatQueryReturnsRows("SELECT * FROM all_types WHERE contract = '64657374696e6720736f6d652076616c7565730a'", 1);
    }

    @Test
    public void testExecuteParametrizedInsert() throws SQLException {
        StringBuilder queryString = new StringBuilder("INSERT INTO person (firstname, lastname) VALUES (?, ?)");
        InsertQuery query = new InsertQuery(queryString, dataSource);

        StringColumnValue param1 = new StringColumnValue("Lasse");
        StringColumnValue param2 = new StringColumnValue("Kukkonen");
        query.addParam(param1);
        query.addParam(param2);

        int result = query.execute();
        assertEquals(4, result);

        assertThatQueryReturnsRows("SELECT * FROM person WHERE firstname = 'Lasse'", 1);
        assertThatQueryReturnsRows("SELECT * FROM person WHERE lastname = 'Kukkonen'", 1);
    }
}
