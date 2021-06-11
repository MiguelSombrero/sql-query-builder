package validation;

import java.util.regex.Pattern;

public class StringValuePatternValidator implements Validator<String> {
    private static final String VALID_STRING_VALUE_PATTERN = "^(?!.*\\s\\s)(?!.*--)\\%?[\\w\\-\\s]+\\.?\\w*\\%?$";

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
        return Pattern.matches(VALID_STRING_VALUE_PATTERN, input);
    }
}
