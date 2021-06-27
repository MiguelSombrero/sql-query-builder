package builder.appender;

import builder.query.SQLQueryBuilder;
import testutils.DatabaseConnection;
import org.junit.Before;
import query.SQLStatement;
import builder.query.select.table.Table;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

public class SubQueryAppenderTest {
    private SQLQueryBuilder SQLQueryBuilder;

    @Before
    public void setUp() throws SQLException {
        SQLQueryBuilder = new SQLQueryBuilder(DatabaseConnection.getH2DataSource());
    }

    @Test
    public void testAppendSubQuery() {
        SQLStatement query = new SQLStatement(new StringBuilder());
        Table table = SQLQueryBuilder.select().all().from().table("person");
        SubQueryAppender.appendSubQuery(query, table);
        assertEquals("(SELECT * FROM person)", query.toString());
    }

}
