package factory;

import builder.Builder;
import builder.condition.Condition;
import builder.condition.Negation;
import validation.DateValidator;
import validation.InputValidator;
import validation.StringValidator;

import javax.xml.bind.ValidationException;

public class WhereClauseFactory {
    private static InputValidator validator = new InputValidator(new StringValidator());

    public static Negation valueOf(String operand) throws ValidationException {
        validator.validOrThrow(operand);
        return new Negation(new StringBuilder(operand));
    }

    public static Condition exists(Builder query) {
        return new Condition(new StringBuilder("EXISTS (" + query.build() + ")"));
    }

    public static Condition notExists(Builder query) {
        return new Condition(new StringBuilder("NOT EXISTS (" + query.build() + ")"));
    }
}
