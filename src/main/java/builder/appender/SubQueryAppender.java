package builder.appender;

import builder.statement.select.SelectQueryBuilder;
import query.SQLQuery;

public class SubQueryAppender {

    private SQLQuery query;

    public SubQueryAppender(SQLQuery query) {
        this.query = query;
    }

    public void appendConditionWithSubQuery(String condition, SelectQueryBuilder subQuery) {
        query.append(condition);
        query.append("(");
        query.mergeSubQuery(subQuery.build());
        query.append(")");
    }
}
