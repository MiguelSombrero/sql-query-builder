package validation;

import java.util.regex.Pattern;

public class DatePatternValidator implements Validator<String> {
    private static final String VALID_DATE_PATTERN = "^(\\d{4})-(\\d{2})-(\\d{2})$";

    public boolean validate(String input) {
        if (input == null) {
            return false;
        }
        if (!matches(input)) {
            return false;
        }
        return true;
    }

    private boolean matches(String input) {
        return Pattern.matches(VALID_DATE_PATTERN, input);
    }
}
