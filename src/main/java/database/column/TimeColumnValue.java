package database.column;

import java.sql.Time;

public class TimeColumnValue extends AbstractColumnValue<Time> {

    public TimeColumnValue(Time value) {
        super(value);
    }

    @Override
    public String toString() {
        return "'" + this.value.toString() + "'";
    }
}
