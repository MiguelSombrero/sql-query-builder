package validation;

public abstract class ValidatorTemplate implements Validator {

    public boolean validate(String input) {
        if (input == null) {
            return false;
        }
        if (input.length() == 0) {
            return false;
        }
        if (input.length() > 255) {
            return false;
        }
        if (!matches(input)) {
            return false;
        }
        return true;
    }

    public abstract boolean matches(String input);
}
