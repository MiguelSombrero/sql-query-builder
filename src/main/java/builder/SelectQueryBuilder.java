package builder;

import builder.statement.FromStatementBuilder;

public class SelectQueryBuilder extends QueryBuilder {
    private boolean firstValue;

    public SelectQueryBuilder(StringBuilder builder) {
        this.builder = builder;
        this.firstValue = true;
    }

    public SelectQueryBuilder value(String value) {
        if (!firstValue) {
            appendComma();
            appendBlank();
        }

        append(value);
        firstValue = false;
        return this;
    }

    public SelectQueryBuilder as(String text) {
        appendAS();
        append(text);
        return this;
    }

    public FromStatementBuilder from(String table) {
        append(" FROM ");
        append(table);
        return new FromStatementBuilder(this.builder);
    }

    private void appendComma() {
        this.builder = this.builder.append(",");
    }

    private void appendAS() {
        this.builder = this.builder.append(" AS ");
    }
}
