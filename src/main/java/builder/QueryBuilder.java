package builder;

public class QueryBuilder implements Builder {
    protected StringBuilder builder;

    public String build() {
        append(";");
        return builder.toString();
    }

    protected void append(String text) {
        this.builder = this.builder.append(text);
    }

    protected void append(Integer value) {
        this.builder = this.builder.append(value);
    }

    protected void appendBlank() {
        this.builder = this.builder.append(" ");
    }

}
