package validation;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;

public class TimePatternValidator implements Validator<String> {

    public boolean validate(String input) {
        if (input == null) {
            return false;
        }

        try {
            LocalTime.parse(input);
        } catch (DateTimeParseException e) {
            return false;
        }

        return true;
    }
}
