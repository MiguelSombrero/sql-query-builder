package builder.utils;

import testutils.DatabaseConnection;
import factory.SelectQueryFactory;
import org.junit.Before;
import query.SQLQuery;
import builder.statement.select.table.Table;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

public class SubSQLQueryAppenderTest {
    private SelectQueryFactory selectQueryFactory;

    @Before
    public void setUp() throws SQLException {
        selectQueryFactory = new SelectQueryFactory(DatabaseConnection.getDataSource());
    }

    @Test
    public void testAppendSubQuery() {
        SQLQuery SQLQuery = new SQLQuery(new StringBuilder());
        SubQueryAppender subQueryAppender = new SubQueryAppender(SQLQuery);
        Table table = selectQueryFactory.select().column("*").from().table("person");
        subQueryAppender.appendConditionWithSubQuery("ALL ", table);
        assertEquals("ALL (SELECT * FROM person)", SQLQuery.build());
    }

}
