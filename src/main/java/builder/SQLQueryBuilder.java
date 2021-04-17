package builder;

public abstract class SQLQueryBuilder implements Builder {
    protected StringBuilder builder;

    public String build() {
        append(";");
        return builder.toString();
    }

    public void append(String text) {
        this.builder = this.builder.append(text);
    }

    public void append(Integer value) {
        this.builder = this.builder.append(value);
    }
}
