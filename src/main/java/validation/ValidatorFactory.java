package validation;

public class ValidatorFactory {

    /**
     * Validator that accepts String values, Dates and DateTimes as input.
     * Validation fails if NO ONE of the validators returns 'true'. Validator
     * is used in cases where user gives input as String and it can represent
     * String, Date or DateTime.
     *
     * @return Validator that throws an exception if validation fails
     */
    public static Validator exceptionThrowingStringValueValidator() {
        return new ExceptionThrowingValidator(new OrValidator(
                stringValueValidator(), dateValidator(), dateTimeValidator()));
    }

    public static Validator exceptionThrowingDateValidator() {
        return new ExceptionThrowingValidator(dateValidator());
    }

    public static Validator exceptionThrowingDateTimeValidator() {
        return new ExceptionThrowingValidator(dateTimeValidator());
    }

    public static Validator exceptionThrowingIntegerValidator() {
        return new ExceptionThrowingValidator(integerValidator());
    }

    public static Validator exceptionThrowingDoubleValidator() {
        return new ExceptionThrowingValidator(doubleValidator());
    }

    public static Validator exceptionThrowingNameValidator() {
        return new ExceptionThrowingValidator(stringValidator());
    }

    public static Validator stringValidator() {
        return new StringPatternValidator();
    }

    public static Validator stringValueValidator() {
        return new StringValuePatternValidator();
    }

    public static Validator dateValidator() {
        return new DatePatternValidator();
    }

    public static Validator dateTimeValidator() {
        return new DateTimePatternValidator();
    }

    public static Validator integerValidator() {
        return new IntegerValidator();
    }

    public static Validator doubleValidator() {
        return new DoubleValidator();
    }
}
