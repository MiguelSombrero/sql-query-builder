package database.column;

import validation.Validator;
import validation.ValidatorFactory;

public class IntegerColumnValue extends AbstractColumnValue<Integer> {
    private static Validator validator = ValidatorFactory.exceptionThrowingIntegerValidator();

    public IntegerColumnValue(Object value) {
        super(value);
        validator.validate(value);
    }
}
