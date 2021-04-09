package builder;

public class QueryBuilder implements Builder {
    protected StringBuilder builder;

    public String build() {
        return builder.toString();
    }

}
