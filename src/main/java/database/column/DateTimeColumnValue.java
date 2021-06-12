package database.column;

import java.time.LocalDateTime;

public class DateTimeColumnValue extends AbstractColumnValue<LocalDateTime> {

    public DateTimeColumnValue(LocalDateTime value) {
        super(value);
    }

    @Override
    public String toString() {
        return "'" + this.value.toString() + "'";
    }
}
