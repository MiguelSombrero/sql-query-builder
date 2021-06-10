package database.row;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.DateConverter;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.sql.Blob;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.sql.Date;
import java.util.Map;

public class Row {
    protected static Logger logger = LoggerFactory.getLogger(Row.class);

    private Map<String, Object> columns;

    public Row(Map<String, Object> columns) {
        this.columns = columns;
    }

    public int getColumnCount() {
        return this.columns.size();
    }

    public String getString(String columnName) {
        Object object = getObject(columnName);
        return castToType(String.class, object);
    }

    public int getInteger(String columnName) {
        Object object = getObject(columnName);
        return castToType(Integer.class, object);
    }

    public BigInteger getBigInteger(String columnName) {
        Object object = getObject(columnName);
        String stringValue = String.valueOf(object);
        return new BigInteger(stringValue);
    }

    public long getLong(String columnName) {
        Object object = getObject(columnName);
        return castToType(Long.class, object);
    }

    public byte[] getBytes(String columnName) {
        Object object = getObject(columnName);
        String stringValue = String.valueOf(object);
        return stringValue.getBytes(StandardCharsets.UTF_8);
    }

    public Blob getBlob(String columnName) {
        Object object = getObject(columnName);
        return castToType(Blob.class, object);
    }

    public double getDouble(String columnName) {
        Object object = getObject(columnName);
        return castToType(Double.class, object);
    }

    public boolean getBoolean(String columnName) {
        Object object = getObject(columnName);
        return castToType(Boolean.class, object);
    }

    public LocalDate getLocalDate(String columnName) {
        Object object = getObject(columnName);
        Date date = castToType(Date.class, object);
        return DateConverter.dateToLocalDate(date);
    }

    public LocalDateTime getLocalDateTime(String columnName) {
        Object object = getObject(columnName);
        Timestamp timestamp = castToType(Timestamp.class, object);
        return DateConverter.timestampToLocalDateTime(timestamp);
    }

    private Object getObject(String columnName) {
        return this.columns.get(columnName.toLowerCase());
    }

    private <T> T castToType(Class<T> type, Object value) {
        return type.cast(value);
    }
}
