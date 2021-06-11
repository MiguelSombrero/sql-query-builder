package validation;

public class ExceptionThrowingValidator implements Validator<Object> {

    Validator validator;

    public ExceptionThrowingValidator(Validator validator) {
        this.validator = validator;
    }

    public boolean validate(Object input) {
        if (!validator.validate(input)) {
            throw new IllegalArgumentException(input + " is not valid input!");
        }
        return true;
    }
}
