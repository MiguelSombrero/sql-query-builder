package builder.appender;

import database.column.*;
import query.Clause;
import validation.Validator;
import validation.ValidatorFactory;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

public class ValueAppender {
    private static Validator<String> timestampValidator = ValidatorFactory.exceptionThrowingTimestampValidator();
    private static Validator<String> timeValidator = ValidatorFactory.exceptionThrowingTimeValidator();
    private static Validator<String> dateValidator = ValidatorFactory.exceptionThrowingDateValidator();
    private static Validator<String> stringValueValidator = ValidatorFactory.exceptionThrowingStringValueValidator();
    private static Validator<byte[]> byteArrayValidator = ValidatorFactory.exceptionThrowingByteArrayValidator();

    public static void appendStringParam(Clause clause, String value) {
        stringValueValidator.validate(value);
        clause.append("?");
        clause.addParam(new StringColumnValue(value));
    }

    public static void appendIntParam(Clause clause, int value) {
        clause.append("?");
        clause.addParam(new IntegerColumnValue(value));
    }

    public static void appendShortParam(Clause clause, short value) {
        clause.append("?");
        clause.addParam(new ShortColumnValue(value));
    }

    public static void appendLongParam(Clause clause, long value) {
        clause.append("?");
        clause.addParam(new LongColumnValue(value));
    }

    public static void appendDoubleParam(Clause clause, double value) {
        clause.append("?");
        clause.addParam(new DoubleColumnValue(value));
    }

    public static void appendBigDecimalParam(Clause clause, BigDecimal value) {
        clause.append("?");
        clause.addParam(new BigDecimalColumnValue(value));
    }

    public static void appendDateParam(Clause clause, String value) {
        dateValidator.validate(value);
        clause.append("?");
        clause.addParam(new DateColumnValue(Date.valueOf(value)));
    }

    public static void appendTimeParam(Clause clause, String value) {
        timeValidator.validate(value);
        clause.append("?");
        clause.addParam(new TimeColumnValue(Time.valueOf(value)));
    }

    public static void appendTimestampParam(Clause clause, String value) {
        timestampValidator.validate(value);
        clause.append("?");
        clause.addParam(new TimestampColumnValue(Timestamp.valueOf(value)));
    }

    public static void appendBooleanParam(Clause clause, boolean value) {
        clause.append("?");
        clause.addParam(new BooleanColumnValue(value));
    }

    public static void appendByteArrayParam(Clause clause, byte[] value) {
        byteArrayValidator.validate(value);
        clause.append("?");
        clause.addParam(new ByteArrayColumnValue(value));
    }
}
