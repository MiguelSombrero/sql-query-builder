package database.column;

import java.sql.Date;

public class DateColumnValue extends AbstractColumnValue<Date> {

    public DateColumnValue(Date value) {
        super(value);
    }

    @Override
    public String toString() {
        return "'" + this.value.toString() + "'";
    }
}
