package validation;

import java.util.regex.Pattern;

public class DateValidator extends ValidatorTemplate {
    private static final String VALID_DATE_PATTERN = "^(\\d{4})-(\\d{2})-(\\d{2})(\\s(\\d{2}):(\\d{2}):(\\d{2}))?$";

    @Override
    public boolean matches(String input) {
        return Pattern.matches(VALID_DATE_PATTERN, input);
    }
}
