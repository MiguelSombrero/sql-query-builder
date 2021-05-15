package validation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Pattern;

public class StringValidator {
    protected static Logger logger = LoggerFactory.getLogger(StringValidator.class);

    private static final String VALID_STRING_PATTERN = "^\\*|\\?|\\w{1,65534}$";

    public static boolean validate(String input){
        if (input == null) {
            return false;
        }
        return Pattern.matches(VALID_STRING_PATTERN, input);
    }
}
