package database.column;

import java.time.LocalDate;

public class DateColumnValue extends AbstractColumnValue<LocalDate> {

    public DateColumnValue(LocalDate value) {
        super(value);
    }

    @Override
    public String toString() {
        return "'" + this.value.toString() + "'";
    }
}
