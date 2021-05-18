package validation;

public class WildCardParameterValidator implements Validator {

    public boolean validate(String input) {
        if (input == null) {
            return false;
        }
        if (!input.equals("?")) {
            return false;
        }

        return true;
    }
}
