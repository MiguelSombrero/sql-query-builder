package builder.clause;

import builder.SQLQueryBuilder;

public class As extends SQLQueryBuilder {

    public As(StringBuilder builder) {
        this.builder = builder;
    }

    public Value value(String value) {
        append(", ");
        append(value);
        return new Value(this.builder);
    }

    public From from(String table) {
        append(" FROM ");
        append(table);
        return new From(this.builder);
    }

}
