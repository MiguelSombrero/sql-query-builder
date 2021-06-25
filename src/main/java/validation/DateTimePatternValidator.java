package validation;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

public class DateTimePatternValidator implements Validator<String> {
    private static final String VALID_DATETIME_PATTERN = "^(\\d{4})-(\\d{2})-(\\d{2})\\s(\\d{2}):(\\d{2}):(\\d{2})$";

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public boolean validate(String input) {
        if (input == null) {
            return false;
        }

        try {
            LocalDateTime.parse(input, formatter);
        } catch (DateTimeParseException e) {
            return false;
        }

        return true;
    }

    private boolean matches(String input) {
        return Pattern.matches(VALID_DATETIME_PATTERN, input);
    }
}
