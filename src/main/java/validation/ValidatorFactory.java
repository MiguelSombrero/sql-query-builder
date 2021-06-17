package validation;

public class ValidatorFactory {

    /**
     * Validator that accepts String values as input. Validator
     * is used in cases where user gives input as String. This validator is
     * not used for validating column and table names and other strings
     * that are not values.
     *
     * @return Validator that throws an exception if validation fails
     */
    public static Validator<String> exceptionThrowingStringValueValidator() {
        return new ExceptionThrowingValidator<>(stringValueValidator());
    }

    public static Validator<String> exceptionThrowingDateValidator() {
        return new ExceptionThrowingValidator<>(dateValidator());
    }

    public static Validator<String> exceptionThrowingDateTimeValidator() {
        return new ExceptionThrowingValidator<>(dateTimeValidator());
    }

    public static Validator<Integer> exceptionThrowingIntegerValidator() {
        return new ExceptionThrowingValidator<>(integerValidator());
    }

    public static Validator<Double> exceptionThrowingDoubleValidator() {
        return new ExceptionThrowingValidator<>(doubleValidator());
    }

    public static Validator<String> exceptionThrowingNameValidator() {
        return new ExceptionThrowingValidator<>(stringValidator());
    }

    public static Validator<byte[]> exceptionThrowingByteArrayValidator() {
        return new ExceptionThrowingValidator<>(byteArrayValidator());
    }

    public static Validator<String> stringValidator() {
        return new StringPatternValidator();
    }

    public static Validator<String> stringValueValidator() {
        return new StringValuePatternValidator();
    }

    public static Validator<String> dateValidator() {
        return new DatePatternValidator();
    }

    public static Validator<String> dateTimeValidator() {
        return new DateTimePatternValidator();
    }

    public static Validator<Integer> integerValidator() {
        return new IntegerValidator();
    }

    public static Validator<Double> doubleValidator() {
        return new DoubleValidator();
    }

    public static Validator<byte[]> byteArrayValidator() {
        return new ByteArrayPatternValidator();
    }
}
