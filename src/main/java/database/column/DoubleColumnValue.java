package database.column;

import validation.Validator;
import validation.ValidatorFactory;

public class DoubleColumnValue extends AbstractColumnValue<Double> {
    private static Validator validator = ValidatorFactory.exceptionThrowingDoubleValidator();

    public DoubleColumnValue(Object value) {
        super(value);
        validator.validate(value);
    }
}
