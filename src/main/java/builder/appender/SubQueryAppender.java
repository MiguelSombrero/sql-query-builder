package builder.appender;

import builder.query.select.SelectQueryBuilder;
import clause.Clause;

public class SubQueryAppender {

    public static void appendSubQuery(Clause clause, SelectQueryBuilder subQuery) {
        clause.append("(");
        clause.mergeSubQuery(subQuery.build());
        clause.append(")");
    }
}
