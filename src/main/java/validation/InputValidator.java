package validation;

import javax.xml.bind.ValidationException;
import java.util.Arrays;

public class InputValidator {

    Validator[] validators;

    public InputValidator(Validator ...validators) {
        this.validators = validators;
    }

    public boolean validOrThrow(String value) throws ValidationException {
        Arrays.stream(validators)
                .filter(validator -> validator.validate(value))
                .findAny()
                .orElseThrow(() -> new ValidationException(value + " is not valid input!"));

        return true;
    }
}
