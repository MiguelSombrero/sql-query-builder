package factory;

import builder.QueryBuilder;
import query.SQLQuery;
import builder.condition.Condition;
import builder.condition.Negation;
import validation.Validator;

public class WhereClauseFactory {
    private static Validator validator = ValidatorFactory.exceptionThrowingColumnValidator();

    public static Negation valueOf(String operand) {
        validator.validate(operand);
        return new Negation(createQuery(operand));
    }

    public static Condition exists(QueryBuilder query) {
        return new Condition(createQuery("EXISTS (" + query.build() + ")"));
    }

    public static Condition notExists(QueryBuilder query) {
        return new Condition(createQuery("NOT EXISTS (" + query.build() + ")"));
    }

    private static SQLQuery createQuery(String clause) {
        return new SQLQuery(new StringBuilder(clause));
    }
}
