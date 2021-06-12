package database.column;

public class StringColumnValue extends AbstractColumnValue<String> {

    public StringColumnValue(String value) {
        super(value);
    }

    @Override
    public String toString() {
        return "'" + this.value + "'";
    }
}
