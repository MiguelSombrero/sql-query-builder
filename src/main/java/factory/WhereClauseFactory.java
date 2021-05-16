package factory;

import builder.Builder;
import builder.condition.Condition;
import builder.condition.Negation;
import validation.DateValidator;
import validation.StringValidator;

import javax.xml.bind.ValidationException;

public class WhereClauseFactory {

    public static Negation valueOf(String operand) throws ValidationException {
        if (!isValidInput(operand)) {
            throw new ValidationException(operand + " is not valid input!");
        }
        return new Negation(new StringBuilder(operand));
    }

    public static Condition exists(Builder query) {
        return new Condition(new StringBuilder("EXISTS (" + query.build() + ")"));
    }

    public static Condition notExists(Builder query) {
        return new Condition(new StringBuilder("NOT EXISTS (" + query.build() + ")"));
    }

    private static boolean isValidInput(String value) {
        return StringValidator.validate(value) || DateValidator.validate(value);
    }
}
