package factory;

import builder.statement.select.SelectQueryBuilder;
import query.SQLQuery;
import builder.condition.Condition;
import builder.condition.Negation;
import query.SelectQuery;
import validation.Validator;

public class WhereClauseFactory {
    private static Validator validator = ValidatorFactory.exceptionThrowingColumnValidator();

    public static Negation valueOf(String operand) {
        validator.validate(operand);
        return new Negation(createQuery(operand));
    }

    public static Condition exists(SelectQueryBuilder query) {
        return new Condition(createQuery("EXISTS (" + query.build() + ")"));
    }

    public static Condition notExists(SelectQueryBuilder query) {
        return new Condition(createQuery("NOT EXISTS (" + query.build() + ")"));
    }

    private static SelectQuery createQuery(String clause) {
        return new SelectQuery(new StringBuilder(clause));
    }
}
