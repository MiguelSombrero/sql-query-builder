package factory;

import builder.condition.Negation;
import validation.DateValidator;
import validation.StringValidator;

import javax.xml.bind.ValidationException;

public class HavingClauseFactory {

    public static Negation count(String column) throws ValidationException {
        return appendOperationAndReturn("COUNT", column);
    }

    public static Negation sum(String column) throws ValidationException {
        return appendOperationAndReturn("SUM", column);
    }

    public static Negation avg(String column) throws ValidationException {
        return appendOperationAndReturn("AVG", column);
    }

    public static Negation max(String column) throws ValidationException {
        return appendOperationAndReturn("MAX", column);
    }

    public static Negation min(String column) throws ValidationException {
        return appendOperationAndReturn("MIN", column);
    }

    private static Negation appendOperationAndReturn(String operation, String column) throws ValidationException {
        if (!isValidInput(column)) {
            throw new ValidationException(column + " is not valid input!");
        }
        StringBuilder builder = new StringBuilder(operation + "(" + column + ")");
        return new Negation(builder);
    }

    private static boolean isValidInput(String value) {
        return StringValidator.validate(value) || DateValidator.validate(value);
    }
}
