package validation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Pattern;

public class IntegerValidator {
    protected static Logger logger = LoggerFactory.getLogger(IntegerValidator.class);

    private static final String VALID_INTEGER_PATTERN = "^-?\\d+$";

    public static boolean validate(int input){
        if (input >= Integer.MAX_VALUE) {
            return false;
        }
        if (input <= Integer.MIN_VALUE) {
            return false;
        }
        return Pattern.matches(VALID_INTEGER_PATTERN, String.valueOf(input));
    }
}
