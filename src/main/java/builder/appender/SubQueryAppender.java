package builder.appender;

import builder.query.select.SelectQueryBuilder;
import query.Statement;

public class SubQueryAppender {

    public static void appendSubQuery(Statement statement, SelectQueryBuilder subQuery) {
        statement.append("(");
        statement.mergeStatement(subQuery.build().getStatement());
        statement.append(")");
    }
}
