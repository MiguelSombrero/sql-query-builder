package builder.statement;

import builder.QueryBuilder;

public class WhereStatementBuilder extends QueryBuilder {

    public WhereStatementBuilder(StringBuilder builder) {
        this.builder = builder;
    }

    public WhereStatementBuilder equals(String value) {
        append(" = ");
        appendSingleQuote();
        append(value);
        appendSingleQuote();
        return this;
    }

    public WhereStatementBuilder equals(Integer value) {
        append(" = ");
        append(value);
        return this;
    }

    public WhereStatementBuilder isGreaterThan(String value) {
        append(" > ");
        appendSingleQuote();
        append(value);
        appendSingleQuote();
        return this;
    }

    public WhereStatementBuilder isGreaterThan(Integer value) {
        append(" > ");
        append(value);
        return this;
    }

    private void appendSingleQuote() {
        this.builder = this.builder.append("'");
    }

}
