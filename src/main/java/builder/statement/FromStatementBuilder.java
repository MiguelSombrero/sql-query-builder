package builder.statement;

import builder.QueryBuilder;

public class FromStatementBuilder extends QueryBuilder {

    public FromStatementBuilder(StringBuilder builder) {
        this.builder = builder;
    }

    public FromStatementBuilder alias(String alias) {
        appendBlank();
        append(alias);
        return this;
    }

    public WhereStatementBuilder where(String value) {
        append(" WHERE ");
        append(value);
        return new WhereStatementBuilder(this.builder);
    }
}
