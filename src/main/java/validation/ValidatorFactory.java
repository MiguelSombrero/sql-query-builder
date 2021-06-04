package validation;

public class ValidatorFactory {

    public static Validator exceptionThrowingStringValueValidator() {
        return new ExceptionThrowingValidator(new OrValidator(
                stringValueValidator(), dateValidator(), wildCardParameterValidator()));
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

    public static Validator wildCardParameterValidator() {
        return new WildCardValidator("?");
    }
}
