package database;

import java.util.Date;

public class Row {
    private Object[] columns;

    public Row(Object[] columns) {
        this.columns = columns;
    }

    public int length() {
        return this.columns.length;
    }

    public String getStringFrom(int index) {
        return (String) getObjectFrom(index);
    }

    public int getIntegerFrom(int index) {
        return (int) getObjectFrom(index);
    }

    public long getLongFrom(int index) {
        return (long) getObjectFrom(index);
    }

    public double getDoubleFrom(int index) {
        return (double) getObjectFrom(index);
    }

    public Date getSDateFrom(int index) {
        return (Date) getObjectFrom(index);
    }

    private Object getObjectFrom(int index) {
        return this.columns[index];
    }
}
