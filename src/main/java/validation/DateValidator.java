package validation;

import java.util.regex.Pattern;

public class DateValidator implements Validator {
    private static final String VALID_DATE_PATTERN = "^(\\d{4})-(\\d{2})-(\\d{2})(\\s(\\d{2}):(\\d{2}):(\\d{2}))?$";

    public boolean validate(String input){
        if (input == null) {
            return false;
        }
        return Pattern.matches(VALID_DATE_PATTERN, input);
    }
}
