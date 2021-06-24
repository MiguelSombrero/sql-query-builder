package builder.appender;

import builder.query.select.SelectQueryBuilder;
import query.Clause;

public class SubQueryAppender {

    public static void appendSubQuery(Clause clause, SelectQueryBuilder subQuery) {
        clause.append("(");
        clause.mergeClause(subQuery.build().getClause());
        clause.append(")");
    }
}
