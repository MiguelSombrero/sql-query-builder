package database.row;

import database.column.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
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
        StringColumnValue value = (StringColumnValue) getColumnValue(columnName);
        return value.getValue();
    }

    public int getInteger(String columnName) {
        IntegerColumnValue value = (IntegerColumnValue) getColumnValue(columnName);
        return value.getValue();
    }

    public long getLong(String columnName) {
        LongColumnValue value = (LongColumnValue) getColumnValue(columnName);
        return value.getValue();
    }

    public double getDouble(String columnName) {
        DoubleColumnValue value = (DoubleColumnValue) getColumnValue(columnName);
        return value.getValue();
    }

    public BigDecimal getBigDecimal(String columnName) {
        BigDecimalColumnValue value = (BigDecimalColumnValue) getColumnValue(columnName);
        return value.getValue();
    }

    public byte[] getBytes(String columnName) {
        ByteArrayColumnValue value = (ByteArrayColumnValue) getColumnValue(columnName);
        return value.getValue();
    }

    public boolean getBoolean(String columnName) {
        BooleanColumnValue value = (BooleanColumnValue) getColumnValue(columnName);
        return value.getValue();
    }

    public Date getDate(String columnName) {
        DateColumnValue value = (DateColumnValue) getColumnValue(columnName);
        return value.getValue();
    }

    public Timestamp getTimestamp(String columnName) {
        TimestampColumnValue value = (TimestampColumnValue) getColumnValue(columnName);
        return value.getValue();
    }

    private ColumnValue getColumnValue(String columnName) {
        ColumnValue value;

        try {
            value = this.columns.get(columnName.toLowerCase());
        } catch (NullPointerException e) {
            logger.info("Did not find column with column name " + columnName);
            logger.debug(e.getLocalizedMessage());
            throw e;
        }

        return value;
    }
}
