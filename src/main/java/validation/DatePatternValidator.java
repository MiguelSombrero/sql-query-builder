package validation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DatePatternValidator implements Validator<String> {
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public boolean validate(String input) {
        if (input == null) {
            return false;
        }

        try {
            LocalDate.parse(input, formatter);
        } catch (DateTimeParseException e) {
            return false;
        }

        return true;
    }
}
