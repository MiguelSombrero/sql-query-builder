package builder;

import builder.clause.Value;

public class Select extends SQLQueryBuilder {

    public Select(StringBuilder builder) {
        this.builder = builder;
    }

    public Value value(String value) {
        append(value);
        return new Value(this.builder);
    }

    public Value distinct(String value) {
        append("DISTINCT ");
        return value(value);
    }
}
