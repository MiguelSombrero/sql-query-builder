package builder.appender;

import builder.query.SQLQueryBuilder;
import testutils.DatabaseConnection;
import org.junit.Before;
import clause.SQLClause;
import builder.query.select.table.Table;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

public class SubQueryAppenderTest {
    private SQLQueryBuilder SQLQueryBuilder;

    @Before
    public void setUp() throws SQLException {
        SQLQueryBuilder = new SQLQueryBuilder(DatabaseConnection.getDataSource());
    }

    @Test
    public void testAppendSubQuery() {
        SQLClause query = new SQLClause(new StringBuilder());
        Table table = SQLQueryBuilder.select().all().from().table("person");
        SubQueryAppender.appendSubQuery(query, table);
        assertEquals("(SELECT * FROM person)", query.toString());
    }

}
