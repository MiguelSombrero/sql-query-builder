package builder.appender;

import builder.statement.select.SelectQueryBuilder;
import query.SQLQuery;

public class SubQueryAppender {

    public static void appendSubQuery(SQLQuery query, SelectQueryBuilder subQuery) {
        query.append("(");
        query.mergeSubQuery(subQuery.build());
        query.append(")");
    }
}
