package builder.clause;

import builder.appender.AggregateFunctionAppender;
import builder.appender.StringAppender;
import builder.query.select.SelectQueryBuilder;
import clause.SQLClause;

public class ConditionClauseBuilder {
    private static AggregateFunctionAppender aggregateFunctionAppender;
    private static ClauseFactory clauseFactory;

    public static Negation valueOf(String column) {
        SQLClause clause = clauseFactory.createClause();
        StringAppender.validateAndAppend(clause, column);
        return new Negation(clause);
    }

    public static Condition exists(SelectQueryBuilder subQuery) {
        SQLClause clause = clauseFactory.createClause();
        clause.append("EXISTS (" + subQuery.build() + ")");
        return new Condition(clause);
    }

    public static Condition notExists(SelectQueryBuilder subQuery) {
        SQLClause clause = clauseFactory.createClause();
        clause.append("NOT EXISTS (" + subQuery.build() + ")");
        return new Condition(clause);
    }

    public static Negation count(String column) {
        SQLClause clause = clauseFactory.createClause();
        aggregateFunctionAppender.count(clause, column);
        return new Negation(clause);
    }

    public static Negation sum(String column) {
        SQLClause clause = clauseFactory.createClause();
        aggregateFunctionAppender.sum(clause, column);
        return new Negation(clause);
    }

    public static Negation avg(String column) {
        SQLClause clause = clauseFactory.createClause();
        aggregateFunctionAppender.avg(clause, column);
        return new Negation(clause);
    }

    public static Negation max(String column) {
        SQLClause clause = clauseFactory.createClause();
        aggregateFunctionAppender.max(clause, column);
        return new Negation(clause);
    }

    public static Negation min(String column) {
        SQLClause clause = clauseFactory.createClause();
        aggregateFunctionAppender.min(clause, column);
        return new Negation(clause);
    }
}
