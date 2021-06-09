package builder.appender;

import testutils.DatabaseConnection;
import query.QueryFactory;
import org.junit.Before;
import query.SQLQuery;
import builder.statement.select.table.Table;
import org.junit.Test;
import builder.appender.SubQueryAppender;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

public class SubSQLQueryAppenderTest {
    private QueryFactory queryFactory;

    @Before
    public void setUp() throws SQLException {
        queryFactory = new QueryFactory(DatabaseConnection.getDataSource());
    }

    @Test
    public void testAppendSubQuery() {
        SQLQuery query = new SQLQuery(new StringBuilder());
        SubQueryAppender subQueryAppender = new SubQueryAppender(query);
        Table table = queryFactory.select().all().from().table("person");
        subQueryAppender.appendConditionWithSubQuery("ALL ", table);
        assertEquals("ALL (SELECT * FROM person)", query.toString());
    }

}
