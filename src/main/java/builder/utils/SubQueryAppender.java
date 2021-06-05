package builder.utils;

import query.Query;
import builder.statement.select.SelectQueryBuilder;

public class SubQueryAppender {

    private Query query;

    public SubQueryAppender(Query query) {
        this.query = query;
    }

    public void appendConditionWithSubQuery(String condition, SelectQueryBuilder subQuery) {
        query.append(condition);
        query.append("(");
        query.append(subQuery.build().toString());
        query.append(")");
    }
}
