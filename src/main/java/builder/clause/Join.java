package builder.clause;

import builder.SQLQueryBuilder;

public class Join extends SQLQueryBuilder {

    public Join(StringBuilder builder) {
        this.builder = builder;
    }

    public From on(String condition) {
        append(" ON ");
        append(condition);
        return new From(this.builder);
    }
}
