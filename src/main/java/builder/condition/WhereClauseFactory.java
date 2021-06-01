package builder.condition;

import builder.statement.select.SelectQueryBuilder;
import query.SQLQuery;
import validation.Validator;
import validation.ValidatorFactory;

public class WhereClauseFactory {
    private static Validator validator = ValidatorFactory.exceptionThrowingNameValidator();

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

    private static SQLQuery createQuery(String clause) {
        return new SQLQuery(new StringBuilder(clause));
    }
}
