package builder.clause;

import builder.appender.AggregateFunctionAppender;
import builder.appender.StringAppender;
import builder.query.select.SelectQueryBuilder;
import query.Clause;

public class ConditionClauseBuilder {
    
    public static Negation valueOf(String column) {
        Clause clause = ClauseFactory.createEmptyClause();
        StringAppender.validateAndAppend(clause, column);
        return new Negation(clause);
    }

    public static Condition exists(SelectQueryBuilder subQuery) {
        Clause clause = ClauseFactory.createExistsClause(subQuery);
        return new Condition(clause);
    }

    public static Condition notExists(SelectQueryBuilder subQuery) {
        Clause clause = ClauseFactory.createNotExistsClause(subQuery);
        return new Condition(clause);
    }

    public static Negation count(String column) {
        Clause clause = ClauseFactory.createEmptyClause();
        AggregateFunctionAppender.count(clause, column);
        return new Negation(clause);
    }

    public static Negation sum(String column) {
        Clause clause = ClauseFactory.createEmptyClause();
        AggregateFunctionAppender.sum(clause, column);
        return new Negation(clause);
    }

    public static Negation avg(String column) {
        Clause clause = ClauseFactory.createEmptyClause();
        AggregateFunctionAppender.avg(clause, column);
        return new Negation(clause);
    }

    public static Negation max(String column) {
        Clause clause = ClauseFactory.createEmptyClause();
        AggregateFunctionAppender.max(clause, column);
        return new Negation(clause);
    }

    public static Negation min(String column) {
        Clause clause = ClauseFactory.createEmptyClause();
        AggregateFunctionAppender.min(clause, column);
        return new Negation(clause);
    }
}
