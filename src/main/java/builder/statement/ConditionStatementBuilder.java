package builder.statement;

import builder.appender.AggregateFunctionAppender;
import builder.appender.StringAppender;
import builder.query.select.SelectQueryBuilder;
import query.Statement;

public class ConditionStatementBuilder {
    
    public static Negation valueOf(String column) {
        Statement statement = StatementFactory.createEmptyStatement();
        StringAppender.validateAndAppend(statement, column);
        return new Negation(statement);
    }

    public static Condition exists(SelectQueryBuilder subQuery) {
        Statement statement = StatementFactory.createExistsStatement(subQuery);
        return new Condition(statement);
    }

    public static Condition notExists(SelectQueryBuilder subQuery) {
        Statement statement = StatementFactory.createNotExistsStatement(subQuery);
        return new Condition(statement);
    }

    public static Negation count(String column) {
        Statement statement = StatementFactory.createEmptyStatement();
        AggregateFunctionAppender.count(statement, column);
        return new Negation(statement);
    }

    public static Negation sum(String column) {
        Statement statement = StatementFactory.createEmptyStatement();
        AggregateFunctionAppender.sum(statement, column);
        return new Negation(statement);
    }

    public static Negation avg(String column) {
        Statement statement = StatementFactory.createEmptyStatement();
        AggregateFunctionAppender.avg(statement, column);
        return new Negation(statement);
    }

    public static Negation max(String column) {
        Statement statement = StatementFactory.createEmptyStatement();
        AggregateFunctionAppender.max(statement, column);
        return new Negation(statement);
    }

    public static Negation min(String column) {
        Statement statement = StatementFactory.createEmptyStatement();
        AggregateFunctionAppender.min(statement, column);
        return new Negation(statement);
    }
}
