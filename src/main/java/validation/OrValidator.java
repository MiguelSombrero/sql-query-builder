package validation;

import java.util.Arrays;

public class OrValidator implements Validator<String> {

    Validator[] validators;

    public OrValidator(Validator ...validators) {
        this.validators = validators;
    }

    @Override
    public boolean validate(String input) {
        return Arrays.stream(validators)
                .anyMatch(validator -> validator.validate(input));
    }
}
