package builder.clause;

import builder.appender.AggregateFunctionAppender;
import query.SQLQuery;

public class HavingClauseFactory {

    private static AggregateFunctionAppender appender;

    public static Negation count(String column) {
        SQLQuery query = createQuery();
        appender.count(query, column);
        return new Negation(query);
    }

    public static Negation sum(String column) {
        SQLQuery query = createQuery();
        appender.sum(query, column);
        return new Negation(query);
    }

    public static Negation avg(String column) {
        SQLQuery query = createQuery();
        appender.avg(query, column);
        return new Negation(query);
    }

    public static Negation max(String column) {
        SQLQuery query = createQuery();
        appender.max(query, column);
        return new Negation(query);
    }

    public static Negation min(String column) {
        SQLQuery query = createQuery();
        appender.min(query, column);
        return new Negation(query);
    }

    private static SQLQuery createQuery() {
        return new SQLQuery(new StringBuilder());
    }
}
