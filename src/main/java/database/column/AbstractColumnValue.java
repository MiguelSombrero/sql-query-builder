package database.column;

public class AbstractColumnValue<T> {
    protected Object value;

    public AbstractColumnValue(Object value) {
        this.value = value;
    }

    public Object getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return this.value.toString();
    }
}
