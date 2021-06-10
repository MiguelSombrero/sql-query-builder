package builder.appender;

import database.column.AbstractColumnValue;
import database.column.DoubleColumnValue;
import database.column.IntegerColumnValue;
import database.column.StringColumnValue;
import query.Query;
import validation.Validator;
import validation.ValidatorFactory;

public class ValueAppender {
    private static Validator validator = ValidatorFactory.exceptionThrowingStringValueValidator();

    public static void validateAndAppendString(Query query, String value) {
        validator.validate(value);
        query.append("?");
        StringColumnValue param = new StringColumnValue(value);
        query.addParam(param);
    }

    public static void validateAndAppendInt(Query query, int value) {
        //TODO validate
        query.append("?");
        IntegerColumnValue param = new IntegerColumnValue(value);
        query.addParam(param);
    }

    public static void validateAndAppendDouble(Query query, double value) {
        //TODO validate
        query.append("?");
        DoubleColumnValue param = new DoubleColumnValue(value);
        query.addParam(param);
    }
}
