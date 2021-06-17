package validation;

import java.util.Arrays;

public class OrValidator implements Validator<Object> {

    Validator[] validators;

    public OrValidator(Validator ...validators) {
        this.validators = validators;
    }

    @Override
    public boolean validate(Object input) {
        return Arrays.stream(validators)
                .anyMatch(validator -> validator.validate(input));
    }
}
