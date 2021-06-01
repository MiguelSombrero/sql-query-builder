package factory;

import builder.statement.select.column.AggregateFunction;
import query.SQLQuery;
import builder.condition.Negation;

public class HavingClauseFactory {

    public static Negation count(String column) {
        SQLQuery query = createQuery();
        AggregateFunction aggregateFunction = new AggregateFunction(query);
        aggregateFunction.count(column);
        return new Negation(query);
    }

    public static Negation sum(String column) {
        SQLQuery query = createQuery();
        AggregateFunction aggregateFunction = new AggregateFunction(query);
        aggregateFunction.sum(column);
        return new Negation(query);
    }

    public static Negation avg(String column) {
        SQLQuery query = createQuery();
        AggregateFunction aggregateFunction = new AggregateFunction(query);
        aggregateFunction.avg(column);
        return new Negation(query);
    }

    public static Negation max(String column) {
        SQLQuery query = createQuery();
        AggregateFunction aggregateFunction = new AggregateFunction(query);
        aggregateFunction.max(column);
        return new Negation(query);
    }

    public static Negation min(String column) {
        SQLQuery query = createQuery();
        AggregateFunction aggregateFunction = new AggregateFunction(query);
        aggregateFunction.min(column);
        return new Negation(query);
    }

    private static SQLQuery createQuery() {
        return new SQLQuery(new StringBuilder());
    }
}
