package builder.query.select.column;

import builder.query.SQLQueryBuilder;
import org.junit.Before;
import org.junit.Test;
import testutils.DatabaseConnection;
import testutils.DatabaseTestBaseClass;

import java.sql.SQLException;

public class ColumnValidationTest extends DatabaseTestBaseClass {
    private SQLQueryBuilder sqlQueryBuilder;

    @Before
    public void setUp() throws SQLException {
        initializeDatabase();
        sqlQueryBuilder = new SQLQueryBuilder(DatabaseConnection.getH2DataSource());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSelectColumnWithParameter() {
        sqlQueryBuilder
                .select()
                    .column("*")
                .from()
                    .table("person")
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSelectFirstColumnWithSQLInjection() {
        sqlQueryBuilder
                .select()
                .column(";DROP")
                .from()
                    .table("person")
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSelectColumnWithSQLInjection() {
        sqlQueryBuilder
                .select()
                    .column("firstname")
                    .column(";DROP")
                .from()
                .table("person")
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSelectColumnAliasWithSQLInjection() {
        sqlQueryBuilder
                .select()
                .column("firstname").alias(";DROP")
                .from()
                .table("person")
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSelectAggregateFunctionWithSQLInjection() {
        sqlQueryBuilder
                .select()
                    .count(";DROP")
                .from()
                    .table("person")
                .build();
    }
}
