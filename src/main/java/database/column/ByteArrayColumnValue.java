package database.column;

public class ByteArrayColumnValue extends AbstractColumnValue<byte[]> {

    public ByteArrayColumnValue(byte[] value) {
        super(value);
    }

    @Override
    public String toString() {
        return "'" + this.value + "'";
    }
}
