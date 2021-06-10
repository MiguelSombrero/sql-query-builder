package database.column;

import validation.Validator;
import validation.ValidatorFactory;

public class StringColumnValue extends AbstractColumnValue<String> {
    private static Validator validator = ValidatorFactory.exceptionThrowingStringValueValidator();

    public StringColumnValue(Object value) {
        super(value);
        validator.validate((String) value);
    }

    @Override
    public String toString() {
        return "'" + this.value.toString() + "'";
    }
}
