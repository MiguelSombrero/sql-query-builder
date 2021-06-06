package query.dql;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Row {
    protected static Logger logger = LoggerFactory.getLogger(Row.class);

    private Object[] columns;
    private String[] columnNames;
    private int[] columnTypes;

    public Row() {}

    public void setColumns(Object[] columns) {
        this.columns = columns;
    }

    public void setColumnNames(String[] columnNames) {
        this.columnNames = columnNames;
    }

    public String getColumnNames() {
        return String.join(", ", this.columnNames);
    }

    public String getColumnName(int index) {
        return this.columnNames[index];
    }

    public void setColumnTypes(int[] columnTypes) {
        this.columnTypes = columnTypes;
    }

    public int getColumnType(int index) {
        return this.columnTypes[index];
    }

    public int getColumnCount() {
        return this.columns.length;
    }

    public String getStringFrom(int index) {
        Object object = getObjectFrom(index);
        String value = ObjectCaster.castToString(object);
        return value;
    }

    public int getIntegerFrom(int index) {
        Object object = getObjectFrom(index);
        int value = ObjectCaster.castToInteger(object);
        return value;
    }

    public double getDoubleFrom(int index) {
        Object object = getObjectFrom(index);
        double value = ObjectCaster.castToDouble(object);
        return value;
    }

    public boolean getBooleanFrom(int index) {
        Object object = getObjectFrom(index);
        boolean value = ObjectCaster.castToBoolean(object);
        return value;
    }

    public LocalDate getLocalDateFrom(int index) {
        Object object = getObjectFrom(index);
        LocalDate value = ObjectCaster.castToLocalDate(object);
        return value;
    }

    public LocalDateTime getLocalDateTimeFrom(int index) {
        Object object = getObjectFrom(index);
        LocalDateTime value = ObjectCaster.castToLocalDateTime(object);
        return value;
    }

    public Timestamp getTimestampFrom(int index) {
        Object object = getObjectFrom(index);
        Timestamp value = ObjectCaster.castToTimestamp(object);
        return value;
    }

    private Object getObjectFrom(int index) {
        Object object;

        try {
            object = this.columns[index];
        } catch (IndexOutOfBoundsException e) {
            logger.info("Tried to fetch object from illegal index");
            logger.debug(e.getLocalizedMessage());
            throw e;
        }

        return object;
    }
}
