package database.column;

import java.sql.Timestamp;

public class TimestampColumnValue extends AbstractColumnValue<Timestamp> {

    public TimestampColumnValue(Timestamp value) {
        super(value);
    }

    @Override
    public String toString() {
        return "'" + this.value.toString() + "'";
    }
}
