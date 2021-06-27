package builder.appender;

import database.column.*;
import query.Statement;
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

    public static void appendStringParam(Statement statement, String value) {
        stringValueValidator.validate(value);
        statement.addParam(new StringColumnValue(value));
    }

    public static void appendIntParam(Statement statement, int value) {
        statement.addParam(new IntegerColumnValue(value));
    }

    public static void appendShortParam(Statement statement, short value) {
        statement.addParam(new ShortColumnValue(value));
    }

    public static void appendLongParam(Statement statement, long value) {
        statement.addParam(new LongColumnValue(value));
    }

    public static void appendDoubleParam(Statement statement, double value) {
        statement.addParam(new DoubleColumnValue(value));
    }

    public static void appendBigDecimalParam(Statement statement, BigDecimal value) {
        statement.addParam(new BigDecimalColumnValue(value));
    }

    public static void appendDateParam(Statement statement, String value) {
        dateValidator.validate(value);
        statement.addParam(new DateColumnValue(Date.valueOf(value)));
    }

    public static void appendTimeParam(Statement statement, String value) {
        timeValidator.validate(value);
        statement.addParam(new TimeColumnValue(Time.valueOf(value)));
    }

    public static void appendTimestampParam(Statement statement, String value) {
        timestampValidator.validate(value);
        statement.addParam(new TimestampColumnValue(Timestamp.valueOf(value)));
    }

    public static void appendBooleanParam(Statement statement, boolean value) {
        statement.addParam(new BooleanColumnValue(value));
    }

    public static void appendByteArrayParam(Statement statement, byte[] value) {
        byteArrayValidator.validate(value);
        statement.addParam(new ByteArrayColumnValue(value));
    }
}
