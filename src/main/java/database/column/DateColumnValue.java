package database.column;

import validation.Validator;
import validation.ValidatorFactory;

import java.time.LocalDate;

public class DateColumnValue extends AbstractColumnValue<LocalDate> {
    private static Validator validator = ValidatorFactory.exceptionThrowingDateValidator();

    public DateColumnValue(Object value) {
        super(value);
        validator.validate((String) value);
    }

    @Override
    public String toString() {
        return "'" + this.value.toString() + "'";
    }
}
