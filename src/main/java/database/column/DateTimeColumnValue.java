package database.column;

import validation.Validator;
import validation.ValidatorFactory;

import java.sql.Date;
import java.time.LocalDateTime;

public class DateTimeColumnValue extends AbstractColumnValue<LocalDateTime> {
    private static Validator validator = ValidatorFactory.exceptionThrowingDateTimeValidator();

    public DateTimeColumnValue(Object value) {
        super(value);
        validator.validate((String) value);
    }

    @Override
    public String toString() {
        return "'" + this.value.toString() + "'";
    }
}
