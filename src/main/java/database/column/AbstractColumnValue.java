package database.column;

public class AbstractColumnValue<T> {
    protected T value;

    public AbstractColumnValue(T value) {
        this.value = value;
    }

    public T getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return this.value.toString();
    }
}
