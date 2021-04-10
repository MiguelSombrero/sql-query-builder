package builder.clause;

import builder.SQLQueryBuilder;

public class Value extends SQLQueryBuilder {

    public Value(StringBuilder builder) {
        this.builder = builder;
    }

    public Value value(String value) {
        append(", ");
        append(value);
        return this;
    }

    public As as(String text) {
        append(" AS ");
        append(text);
        return new As(this.builder);
    }

    public From from(String table) {
        append(" FROM ");
        append(table);
        return new From(this.builder);
    }
}
