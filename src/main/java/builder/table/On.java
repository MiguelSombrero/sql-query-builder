package builder.table;

import builder.SQLQueryBuilder;

public class On extends SQLQueryBuilder {

    public On(StringBuilder builder) {
        this.builder = builder;
    }

    public Table on(String condition) {
        append(" ON ");
        append(condition);
        return new Table(this.builder);
    }
}
