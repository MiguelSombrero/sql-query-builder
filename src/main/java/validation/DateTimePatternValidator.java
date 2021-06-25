package validation;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTimePatternValidator implements Validator<String> {
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
}
