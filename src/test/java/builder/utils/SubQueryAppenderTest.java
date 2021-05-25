package builder.utils;

import builder.Query;
import builder.statement.select.table.Table;
import factory.QueryFactory;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SubQueryAppenderTest {

    @Test
    public void testAppendSubQuery() {
        Query query = new Query(new StringBuilder());
        SubQueryAppender subQueryAppender = new SubQueryAppender(query);
        Table table = QueryFactory.select().column("*").from().table("person");
        subQueryAppender.appendConditionWithSubQuery("ALL ", table);
        assertEquals("ALL (SELECT * FROM person)", query.build());
    }

}
