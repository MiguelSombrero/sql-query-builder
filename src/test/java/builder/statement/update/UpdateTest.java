package builder.statement.update;

import query.QueryFactory;
import query.dml.UpdateQuery;
import testutils.DatabaseConnection;
import testutils.DatabaseTestBaseClass;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static builder.clause.WhereClauseFactory.valueOf;
import static org.junit.Assert.assertEquals;

public class UpdateTest extends DatabaseTestBaseClass {
    private QueryFactory queryFactory;
    private FirstColumn baseQuery;

    @Before
    public void setUp() {
        initializeDatabase();
        queryFactory = new QueryFactory(DatabaseConnection.getDataSource());

        this.baseQuery = queryFactory
                .update()
                .table("person");
    }

    @Test
    public void testUpdateIntegerValue() throws SQLException {
        UpdateQuery query = this.baseQuery
                    .column("age").setInt(50)
                .build();

        assertEquals("UPDATE person SET age = 50", query.toString());

        int result = query.execute();

        assertEquals(3, result);
    }

    @Test
    public void testUpdateDoubleValue() throws SQLException {
        UpdateQuery query = this.baseQuery
                .column("age").setDouble(50.5)
                .build();

        assertEquals("UPDATE person SET age = 50.5", query.toString());

        int result = query.execute();

        assertEquals(3, result);
    }

    @Test
    public void testUpdateStringValue() throws SQLException {
        UpdateQuery query = this.baseQuery
                    .column("firstname").setString("Miika-Lassi Kari")
                .build();

        assertEquals("UPDATE person SET firstname = 'Miika-Lassi Kari'", query.toString());

        int result = query.execute();

        assertEquals(3, result);
    }

    @Test
    public void testUpdateDateValue() throws SQLException {
        UpdateQuery query = this.baseQuery
                .column("birthdate").setDate("1985-01-02")
                .build();

        assertEquals("UPDATE person SET birthdate = '1985-01-02'", query.toString());

        int result = query.execute();

        assertEquals(3, result);
    }

    @Test
    public void testUpdateDateTimeValue() throws SQLException {
        UpdateQuery query = this.baseQuery
                .column("birthdate").setDateTime("1985-01-02 21:04:11")
                .build();

        assertEquals("UPDATE person SET birthdate = '1985-01-02 21:04:11'", query.toString());

        int result = query.execute();

        assertEquals(3, result);
    }

    @Test
    public void testUpdateMultipleValues() throws SQLException {
        UpdateQuery query = this.baseQuery
                    .column("firstname").setString("Miika")
                    .column("lastname").setString("Somero")
                    .column("age").setInt(50)
                .build();

        assertEquals("UPDATE person SET firstname = 'Miika', lastname = 'Somero', age = 50", query.toString());

        int result = query.execute();

        assertEquals(3, result);
    }

    @Test
    public void testUpdateWithCondition() throws SQLException {
        UpdateQuery query = this.baseQuery
                    .column("age").setInt(50)
                .where(valueOf("id").equals(1)
                        .or(valueOf("id").equals(2)))
                .build();

        assertEquals("UPDATE person SET age = 50 WHERE id = 1 OR id = 2", query.toString());

        int result = query.execute();

        assertEquals(2, result);
    }
}
