package builder.appender;

import database.column.*;
import query.Clause;
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
    private static Validator<String> stringValueValidator = ValidatorFactory.exceptionThrowingStringValueValidator();
    private static Validator<byte[]> byteArrayValidator = ValidatorFactory.exceptionThrowingByteArrayValidator();

    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void appendStringParam(Clause clause, String value) {
        stringValueValidator.validate(value);
        clause.append("?");
        clause.addParam(new StringColumnValue(value));
    }

    public static void appendIntParam(Clause clause, int value) {
        integerValidator.validate(value);
        clause.append("?");
        clause.addParam(new IntegerColumnValue(value));
    }

    public static void appendLongParam(Clause clause, long value) {
        clause.append("?");
        clause.addParam(new LongColumnValue(value));
    }

    public static void appendDoubleParam(Clause clause, double value) {
        doubleValidator.validate(value);
        clause.append("?");
        clause.addParam(new DoubleColumnValue(value));
    }

    public static void appendDateParam(Clause clause, String value) {
        dateValidator.validate(value);
        clause.append("?");
        clause.addParam(new DateColumnValue(LocalDate.parse(value, dateFormatter)));
    }

    public static void appendDateTimeParam(Clause clause, String value) {
        dateTimeValidator.validate(value);
        clause.append("?");
        clause.addParam(new DateTimeColumnValue(LocalDateTime.parse(value, dateTimeFormatter)));
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
