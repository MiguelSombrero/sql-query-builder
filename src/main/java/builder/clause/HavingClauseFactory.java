package builder.clause;

import builder.appender.AggregateFunctionAppender;
import query.SQLQuery;

public class HavingClauseFactory {

    public static Negation count(String column) {
        SQLQuery query = createQuery();
        AggregateFunctionAppender aggregateFunctionAppender = new AggregateFunctionAppender(query);
        aggregateFunctionAppender.count(column);
        return new Negation(query);
    }

    public static Negation sum(String column) {
        SQLQuery query = createQuery();
        AggregateFunctionAppender aggregateFunctionAppender = new AggregateFunctionAppender(query);
        aggregateFunctionAppender.sum(column);
        return new Negation(query);
    }

    public static Negation avg(String column) {
        SQLQuery query = createQuery();
        AggregateFunctionAppender aggregateFunctionAppender = new AggregateFunctionAppender(query);
        aggregateFunctionAppender.avg(column);
        return new Negation(query);
    }

    public static Negation max(String column) {
        SQLQuery query = createQuery();
        AggregateFunctionAppender aggregateFunctionAppender = new AggregateFunctionAppender(query);
        aggregateFunctionAppender.max(column);
        return new Negation(query);
    }

    public static Negation min(String column) {
        SQLQuery query = createQuery();
        AggregateFunctionAppender aggregateFunctionAppender = new AggregateFunctionAppender(query);
        aggregateFunctionAppender.min(column);
        return new Negation(query);
    }

    private static SQLQuery createQuery() {
        return new SQLQuery(new StringBuilder());
    }
}
