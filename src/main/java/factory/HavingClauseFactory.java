package factory;

import builder.condition.Negation;
import validation.InputValidator;
import validation.StringValidator;

import javax.xml.bind.ValidationException;

public class HavingClauseFactory {
    private static InputValidator validator = new InputValidator(new StringValidator());

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
        validator.validOrThrow(column);
        StringBuilder builder = new StringBuilder(operation + "(" + column + ")");
        return new Negation(builder);
    }

}
