package validation;

import java.util.regex.Pattern;

public class StringValidator extends ValidatorTemplate {
    private static final String VALID_STRING_PATTERN = "^\\*|%?\\w+\\.?\\w*%?$";

    @Override
    public boolean matches(String input) {
        return Pattern.matches(VALID_STRING_PATTERN, input);
    }
}
