package builder.statement.delete;

import query.QueryFactory;
import query.dml.DMLQuery;
import testutils.DatabaseConnection;
import testutils.DatabaseTestBaseClass;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static builder.clause.WhereClauseFactory.valueOf;
import static org.junit.Assert.assertEquals;

public class DeleteTest extends DatabaseTestBaseClass {
    private QueryFactory queryFactory;

    @Before
    public void setUp() {
        initializeDatabase();
        queryFactory = new QueryFactory(DatabaseConnection.getDataSource());
    }

    @Test
    public void testDeleteFromOneTable() throws SQLException {
        DMLQuery query = queryFactory
                .deleteFrom()
                    .table("address")
                .build();

        assertEquals("DELETE FROM address", query.toString());

        int result = query.execute();

        assertEquals(1, result);
    }

    @Test
    public void testDeleteWithCondition() throws SQLException {
        DMLQuery query = queryFactory
                .deleteFrom()
                    .table("address")
                .where(valueOf("person_id").equals(1))
                .build();

        assertEquals("DELETE FROM address WHERE person_id = 1", query.toString());

        int result = query.execute();

        assertEquals(1, result);
    }

    @Test
    public void testDeleteWithMultipleCondition() throws SQLException {
        DMLQuery query = queryFactory
                .deleteFrom()
                    .table("address")
                .where(valueOf("person_id").equals(1)
                        .and(valueOf("city").equals("Oulu")))
                .build();

        assertEquals("DELETE FROM address WHERE person_id = 1 AND city = 'Oulu'", query.toString());

        int result = query.execute();

        assertEquals(0, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeleteTableWithSQLInjection() {
        queryFactory
                .deleteFrom()
                    .table("; DROP TABLE address")
                .build();
    }
}
