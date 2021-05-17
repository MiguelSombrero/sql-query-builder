package validation;

public class ExceptionThrowingValidator implements Validator {

    Validator validator;

    public ExceptionThrowingValidator(Validator validator) {
        this.validator = validator;
    }

    public boolean validate(String input) {
        if (!validator.validate(input)) {
            throw new IllegalArgumentException(input + " is not valid input!");
        }
        return true;
    }
}
