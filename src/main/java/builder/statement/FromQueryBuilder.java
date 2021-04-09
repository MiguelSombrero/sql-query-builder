package builder.statement;

import builder.QueryBuilder;

public class FromQueryBuilder extends QueryBuilder {

    public FromQueryBuilder(StringBuilder builder) {
        this.builder = builder;
    }

    public FromQueryBuilder alias(String alias) {
        appendBlank();
        append(alias);
        return this;
    }
}
