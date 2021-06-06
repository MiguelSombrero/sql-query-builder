package query.dql;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

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
        String value;

        try {
            value = (String) getObjectFrom(index);
        } catch (ClassCastException e) {
            logger.info("Count not cast object to String");
            logger.debug(e.getLocalizedMessage());
            throw e;
        } catch (IndexOutOfBoundsException e) {
            logger.info("No value in given index");
            logger.debug(e.getLocalizedMessage());
            throw e;
        }

        return value;
    }

    public int getIntegerFrom(int index) {
        return (int) getObjectFrom(index);
    }

    public double getDoubleFrom(int index) {
        return (double) getObjectFrom(index);
    }

    public boolean getBooleanFrom(int index) {
        return (boolean) getObjectFrom(index);
    }

    public Date getDateFrom(int index) {
        return (Date) getObjectFrom(index);
    }

    private Object getObjectFrom(int index) {
        return this.columns[index];
    }
}
