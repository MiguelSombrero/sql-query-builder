package validation;

import java.util.regex.Pattern;

public class StringPatternValidator implements Validator {
    private static final String VALID_STRING_PATTERN = "^\\w+\\.?\\w*$";

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

    private boolean matches(String input) {
        return Pattern.matches(VALID_STRING_PATTERN, input);
    }
}
