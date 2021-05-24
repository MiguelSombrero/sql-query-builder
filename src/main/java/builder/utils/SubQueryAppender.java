package builder.utils;

import builder.Query;
import builder.statement.select.SelectQueryBuilder;

public class SubQueryAppender {

    private Query query;

    public SubQueryAppender(Query query) {
        this.query = query;
    }

    public void appendConditionWithSubQuery(String condition, SelectQueryBuilder subQuery) {
        query.append(condition);
        query.append("(");
        query.append(subQuery.build());
        query.append(")");
    }
}
