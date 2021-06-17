package validation;

import java.util.regex.Pattern;

public class DateTimePatternValidator implements Validator<String> {
    private static final String VALID_DATE_TIME_PATTERN = "^(\\d{4})-(\\d{2})-(\\d{2})\\s(\\d{2}):(\\d{2}):(\\d{2})$";

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
        return Pattern.matches(VALID_DATE_TIME_PATTERN, input);
    }
}
