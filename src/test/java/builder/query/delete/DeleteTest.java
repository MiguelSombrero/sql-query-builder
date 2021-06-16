package builder.query.delete;

import builder.query.SQLQueryBuilder;
import query.dml.DeleteQuery;
import testutils.DatabaseConnection;
import testutils.DatabaseTestBaseClass;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static builder.clause.ConditionClauseBuilder.valueOf;
import static org.junit.Assert.assertEquals;

public class DeleteTest extends DatabaseTestBaseClass {
    private SQLQueryBuilder SQLQueryBuilder;

    @Before
    public void setUp() throws SQLException {
        initializeDatabase();
        SQLQueryBuilder = new SQLQueryBuilder(DatabaseConnection.getDataSource());
    }

    @Test
    public void testDeleteFromOneTable() throws SQLException {
        DeleteQuery query = SQLQueryBuilder
                .deleteFrom()
                    .table("address")
                .build();

        assertEquals("DELETE FROM address", query.toString());

        int result = query.execute();

        assertEquals(1, result);
    }

    @Test
    public void testDeleteWithCondition() throws SQLException {
        DeleteQuery query = SQLQueryBuilder
                .deleteFrom()
                    .table("address")
                .where(valueOf("person_id").integerEquals(1))
                .build();

        assertEquals("DELETE FROM address WHERE person_id = 1", query.toString());

        int result = query.execute();

        assertEquals(1, result);
    }

    @Test
    public void testDeleteWithMultipleCondition() throws SQLException {
        DeleteQuery query = SQLQueryBuilder
                .deleteFrom()
                    .table("address")
                .where(valueOf("person_id").integerEquals(1)
                        .and(valueOf("city").stringEquals("Oulu")))
                .build();

        assertEquals("DELETE FROM address WHERE person_id = 1 AND city = 'Oulu'", query.toString());

        int result = query.execute();

        assertEquals(0, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeleteTableWithSQLInjection() {
        SQLQueryBuilder
                .deleteFrom()
                    .table("; DROP TABLE address")
                .build();
    }
}
