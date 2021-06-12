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

    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void appendStringParam(Query query, String value) {
        stringValueValidator.validate(value);
        query.append("?");
        StringColumnValue param = new StringColumnValue(value);
        query.addParam(param);
    }

    public static void appendIntParam(Query query, int value) {
        integerValidator.validate(value);
        query.append("?");
        IntegerColumnValue param = new IntegerColumnValue(value);
        query.addParam(param);
    }

    public static void appendDoubleParam(Query query, double value) {
        doubleValidator.validate(value);
        query.append("?");
        DoubleColumnValue param = new DoubleColumnValue(value);
        query.addParam(param);
    }

    public static void appendDateParam(Query query, String value) {
        dateValidator.validate(value);
        query.append("?");
        DateColumnValue param = new DateColumnValue(LocalDate.parse(value, dateFormatter));
        query.addParam(param);
    }

    public static void appendDateTimeParam(Query query, String value) {
        dateTimeValidator.validate(value);
        query.append("?");
        DateTimeColumnValue param = new DateTimeColumnValue(LocalDateTime.parse(value, dateTimeFormatter));
        query.addParam(param);
    }
}
