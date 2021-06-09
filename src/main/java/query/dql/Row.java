package query.dql;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

public class Row {
    protected static Logger logger = LoggerFactory.getLogger(Row.class);

    private Map<String, Object> columns;

    public Row(Map<String, Object> columns) {
        this.columns = columns;
    }

    public void setColumns(Map<String, Object> columns) {
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
        return castToType(LocalDate.class, object);
    }

    public LocalDateTime getLocalDateTime(String columnName) {
        Object object = getObject(columnName);
        return castToType(LocalDateTime.class, object);
    }

    public Timestamp getTimestamp(String columnName) {
        Object object = getObject(columnName);
        return castToType(Timestamp.class, object);
    }

    private Object getObject(String columnName) {
        return this.columns.get(columnName.toLowerCase());
    }

    private <T> T castToType(Class<T> type, Object value) {
        return type.cast(value);
    }
}
