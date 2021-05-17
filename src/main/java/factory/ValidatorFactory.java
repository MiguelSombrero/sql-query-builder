package factory;

import validation.*;

public class ValidatorFactory {

    public static Validator exceptionThrowingStringOrDateValidator() {
        return new ExceptionThrowingValidator(stringOrDateValidator());
    }

    public static Validator exceptionThrowingStringValidator() {
        return new ExceptionThrowingValidator(stringValidator());
    }

    public static Validator stringOrDateValidator() {
        return new OrValidator(stringValidator(), dateValidator());
    }

    public static Validator stringValidator() {
        return new StringValidator();
    }

    public static Validator dateValidator() {
        return new DateValidator();
    }
}
