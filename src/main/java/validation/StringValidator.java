package validation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Pattern;

public class StringValidator implements Validator{
    private static final String VALID_STRING_PATTERN = "^\\*|%?\\w+\\.?\\w*%?$";

    public boolean validate(String input){
        if (input == null) {
            return false;
        }
        if (input.length() == 0) {
            return false;
        }
        if (input.length() > 255) {
            return false;
        }
        return Pattern.matches(VALID_STRING_PATTERN, input);
    }
}
