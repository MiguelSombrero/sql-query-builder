package builder.clause;

import builder.appender.AggregateFunctionAppender;
import clause.SQLClause;

public class HavingClauseFactory {

    private static AggregateFunctionAppender appender;

    public static Negation count(String column) {
        SQLClause query = createQuery();
        appender.count(query, column);
        return new Negation(query);
    }

    public static Negation sum(String column) {
        SQLClause query = createQuery();
        appender.sum(query, column);
        return new Negation(query);
    }

    public static Negation avg(String column) {
        SQLClause query = createQuery();
        appender.avg(query, column);
        return new Negation(query);
    }

    public static Negation max(String column) {
        SQLClause query = createQuery();
        appender.max(query, column);
        return new Negation(query);
    }

    public static Negation min(String column) {
        SQLClause query = createQuery();
        appender.min(query, column);
        return new Negation(query);
    }

    private static SQLClause createQuery() {
        return new SQLClause(new StringBuilder());
    }
}
