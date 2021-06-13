package builder.appender;

import testutils.DatabaseConnection;
import query.QueryFactory;
import org.junit.Before;
import clause.SQLClause;
import builder.query.select.table.Table;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

public class SubQueryAppenderTest {
    private QueryFactory queryFactory;

    @Before
    public void setUp() throws SQLException {
        queryFactory = new QueryFactory(DatabaseConnection.getDataSource());
    }

    @Test
    public void testAppendSubQuery() {
        SQLClause query = new SQLClause(new StringBuilder());
        Table table = queryFactory.select().all().from().table("person");
        SubQueryAppender.appendSubQuery(query, table);
        assertEquals("(SELECT * FROM person)", query.toString());
    }

}
