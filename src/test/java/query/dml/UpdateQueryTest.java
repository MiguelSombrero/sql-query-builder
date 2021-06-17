package query.dml;

import database.column.StringColumnValue;
import org.junit.Before;
import org.junit.Test;
import testutils.DatabaseConnection;
import testutils.DatabaseTestBaseClass;

import javax.sql.DataSource;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

public class UpdateQueryTest extends DatabaseTestBaseClass {
    private DataSource dataSource;

    @Before
    public void setUp() {
        initializeDatabase();
        dataSource = DatabaseConnection.getDataSource();
    }

    @Test
    public void testExecuteAllFieldsUpdate() throws SQLException {
        StringBuilder queryString = new StringBuilder("UPDATE all_types SET id = 100, hash = 8223372036854775806, age = 32.2, newdate = '2021-03-01', newdatetime = '2021-04-04 21:02:01', created = '2021-05-05 20:02:01', active = false, country = 10, model = 'T1000', brand = 'Saab', disclaimer = 'Great car', description = 'Buy this', contract = '64657374696e6720736f6d652076616c7565730a' WHERE id = 12");
        UpdateQuery query = new UpdateQuery(queryString, dataSource);

        int result = query.execute();
        assertEquals(1, result);

        assertThatQueryReturnsRows("SELECT * FROM all_types WHERE id = 100", 1);
        assertThatQueryReturnsRows("SELECT * FROM all_types WHERE hash = 8223372036854775806", 1);
        assertThatQueryReturnsRows("SELECT * FROM all_types WHERE age = 32.2", 1);
        assertThatQueryReturnsRows("SELECT * FROM all_types WHERE newdate = '2021-03-01'", 1);
        assertThatQueryReturnsRows("SELECT * FROM all_types WHERE newdatetime = '2021-04-04 21:02:01'", 1);
        assertThatQueryReturnsRows("SELECT * FROM all_types WHERE created = '2021-05-05 20:02:01'", 1);
        assertThatQueryReturnsRows("SELECT * FROM all_types WHERE active = false", 2);
        assertThatQueryReturnsRows("SELECT * FROM all_types WHERE country = 10", 1);
        assertThatQueryReturnsRows("SELECT * FROM all_types WHERE model = 'T1000'", 1);
        assertThatQueryReturnsRows("SELECT * FROM all_types WHERE brand = 'Saab'", 1);
        assertThatQueryReturnsRows("SELECT * FROM all_types WHERE disclaimer = 'Great car'", 1);
        assertThatQueryReturnsRows("SELECT * FROM all_types WHERE description = 'Buy this'", 1);
        assertThatQueryReturnsRows("SELECT * FROM all_types WHERE contract = '64657374696e6720736f6d652076616c7565730a'", 1);
    }

    @Test
    public void testExecuteParametrizedUpdate() throws SQLException {
        StringBuilder queryString = new StringBuilder("UPDATE person SET firstname = ?, lastname = ? WHERE id = 1");
        UpdateQuery query = new UpdateQuery(queryString, dataSource);

        StringColumnValue param1 = new StringColumnValue("Lasse");
        StringColumnValue param2 = new StringColumnValue("Kukkonen");
        query.addParam(param1);
        query.addParam(param2);

        int result = query.execute();
        assertEquals(1, result);

        assertThatQueryReturnsRows("SELECT * FROM person WHERE firstname = 'Lasse'", 1);
        assertThatQueryReturnsRows("SELECT * FROM person WHERE lastname = 'Kukkonen'", 1);
    }
}
