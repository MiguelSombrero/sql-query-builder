package validation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Pattern;

public class DateValidator {
    protected static Logger logger = LoggerFactory.getLogger(DateValidator.class);

    private static final String VALID_DATE_PATTERN = "^(\\d{4})-(\\d{2})-(\\d{2})(\\s(\\d{2}):(\\d{2}):(\\d{2}))?$";

    public static boolean validate(String input){
        if (input == null) {
            return false;
        }
        return Pattern.matches(VALID_DATE_PATTERN, input);
    }
}
