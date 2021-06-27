package builder.query.delete;

import builder.query.SQLQueryBuilder;
import query.DeleteQuery;
import testutils.DatabaseConnection;
import testutils.DatabaseTestBaseClass;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static builder.statement.ConditionStatementBuilder.valueOf;
import static org.junit.Assert.assertEquals;

public class DeleteTest extends DatabaseTestBaseClass {
    private SQLQueryBuilder SQLQueryBuilder;

    @Before
    public void setUp() throws SQLException {
        initializeDatabase();
        SQLQueryBuilder = new SQLQueryBuilder(DatabaseConnection.getH2DataSource());
    }

    @Test
    public void testDeleteFromOneTable() throws SQLException {
        DeleteQuery query = SQLQueryBuilder
                .delete()
                    .table("address")
                .build();

        assertEquals("DELETE FROM address", query.toString());

        int result = query.execute();

        assertEquals(1, result);
    }

    @Test
    public void testDeleteWithCondition() throws SQLException {
        DeleteQuery query = SQLQueryBuilder
                .delete()
                    .table("address")
                .where(valueOf("person_id").equalsInteger(1))
                .build();

        assertEquals("DELETE FROM address WHERE person_id = 1", query.toString());

        int result = query.execute();

        assertEquals(1, result);
    }

    @Test
    public void testDeleteWithMultipleCondition() throws SQLException {
        DeleteQuery query = SQLQueryBuilder
                .delete()
                    .table("address")
                .where(valueOf("person_id").equalsInteger(1)
                        .and(valueOf("city").equalsString("Oulu")))
                .build();

        assertEquals("DELETE FROM address WHERE person_id = 1 AND city = 'Oulu'", query.toString());

        int result = query.execute();

        assertEquals(0, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeleteTableWithSQLInjection() {
        SQLQueryBuilder
                .delete()
                    .table("; DROP TABLE address")
                .build();
    }
}
