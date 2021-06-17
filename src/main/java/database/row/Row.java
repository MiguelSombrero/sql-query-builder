package database.row;

import database.column.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

public class Row {
    protected static Logger logger = LoggerFactory.getLogger(Row.class);

    private Map<String, ColumnValue> columns;

    public Row(Map<String, ColumnValue> columns) {
        this.columns = columns;
    }

    public int getColumnCount() {
        return this.columns.size();
    }

    public String getString(String columnName) {
        StringColumnValue value = (StringColumnValue) getObject(columnName);
        return value.getValue();
    }

    public int getInteger(String columnName) {
        IntegerColumnValue value = (IntegerColumnValue) getObject(columnName);
        return value.getValue();
    }

    public long getLong(String columnName) {
        LongColumnValue value = (LongColumnValue) getObject(columnName);
        return value.getValue();
    }

    public byte[] getBytes(String columnName) {
        ByteArrayColumnValue value = (ByteArrayColumnValue) getObject(columnName);
        return value.getValue();
    }

    public double getDouble(String columnName) {
        DoubleColumnValue value = (DoubleColumnValue) getObject(columnName);
        return value.getValue();
    }

    public boolean getBoolean(String columnName) {
        BooleanColumnValue value = (BooleanColumnValue) getObject(columnName);
        return value.getValue();
    }

    public LocalDate getLocalDate(String columnName) {
        DateColumnValue value = (DateColumnValue) getObject(columnName);
        return value.getValue();
    }

    public LocalDateTime getLocalDateTime(String columnName) {
        DateTimeColumnValue value = (DateTimeColumnValue) getObject(columnName);
        return value.getValue();
    }

    private ColumnValue getObject(String columnName) {
        return this.columns.get(columnName.toLowerCase());
    }
}
