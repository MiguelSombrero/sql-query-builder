package builder.appender;

import query.Query;
import validation.Validator;
import validation.ValidatorFactory;

public class ValueAppender {
    private static Validator validator = ValidatorFactory.exceptionThrowingStringValueValidator();

    public static void validateAndAppendString(Query query, String value) {
        validator.validate(value);
        query.append("?");
        query.addParam(value);
    }

    public static void validateAndAppendInt(Query query, int value) {
        //TODO validate
        query.append("?");
        query.addParam(value);
    }

    public static void validateAndAppendDouble(Query query, double value) {
        //TODO validate
        query.append("?");
        query.addParam(value);
    }
}
