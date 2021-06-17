package validation;

public class ExceptionThrowingValidator<T> implements Validator<T> {

    Validator<T> validator;

    public ExceptionThrowingValidator(Validator<T> validator) {
        this.validator = validator;
    }

    public boolean validate(T input) {
        if (!validator.validate(input)) {
            throw new IllegalArgumentException(input + " is not valid input!");
        }
        return true;
    }
}
