package factory;

import builder.Builder;
import builder.condition.Condition;
import builder.condition.Negation;
import validation.Validator;

public class WhereClauseFactory {
    private static Validator validator = ValidatorFactory.exceptionThrowingColumnValidator();

    public static Negation valueOf(String operand) {
        validator.validate(operand);
        return new Negation(new StringBuilder(operand));
    }

    public static Condition exists(Builder query) {
        return new Condition(new StringBuilder("EXISTS (" + query.build() + ")"));
    }

    public static Condition notExists(Builder query) {
        return new Condition(new StringBuilder("NOT EXISTS (" + query.build() + ")"));
    }
}
