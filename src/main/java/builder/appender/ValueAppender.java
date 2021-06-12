package builder.appender;

import database.column.*;
import query.Query;
import validation.Validator;
import validation.ValidatorFactory;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ValueAppender {
    private static Validator<String> dateTimeValidator = ValidatorFactory.exceptionThrowingDateTimeValidator();
    private static Validator<String> dateValidator = ValidatorFactory.exceptionThrowingDateValidator();
    private static Validator<Double> doubleValidator = ValidatorFactory.exceptionThrowingDoubleValidator();
    private static Validator<Integer> integerValidator = ValidatorFactory.exceptionThrowingIntegerValidator();
    private static Validator<Object> stringValueValidator = ValidatorFactory.exceptionThrowingStringValueValidator();
    private static Validator<byte[]> byteArrayValidator = ValidatorFactory.exceptionThrowingByteArrayValidator();

    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void appendStringParam(Query query, String value) {
        stringValueValidator.validate(value);
        query.append("?");
        query.addParam(new StringColumnValue(value));
    }

    public static void appendIntParam(Query query, int value) {
        integerValidator.validate(value);
        query.append("?");
        query.addParam(new IntegerColumnValue(value));
    }

    public static void appendLongParam(Query query, long value) {
        query.append("?");
        query.addParam(new LongColumnValue(value));
    }

    public static void appendDoubleParam(Query query, double value) {
        doubleValidator.validate(value);
        query.append("?");
        query.addParam(new DoubleColumnValue(value));
    }

    public static void appendDateParam(Query query, String value) {
        dateValidator.validate(value);
        query.append("?");
        query.addParam(new DateColumnValue(LocalDate.parse(value, dateFormatter)));
    }

    public static void appendDateTimeParam(Query query, String value) {
        dateTimeValidator.validate(value);
        query.append("?");
        query.addParam(new DateTimeColumnValue(LocalDateTime.parse(value, dateTimeFormatter)));
    }

    public static void appendBooleanParam(Query query, boolean value) {
        query.append("?");
        query.addParam(new BooleanColumnValue(value));
    }

    public static void appendByteArrayParam(Query query, byte[] value) {
        byteArrayValidator.validate(value);
        query.append("?");
        query.addParam(new ByteArrayColumnValue(value));
    }
}
