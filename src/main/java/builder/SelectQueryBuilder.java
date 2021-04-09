package builder;

import builder.statement.FromQueryBuilder;

public class SelectQueryBuilder extends QueryBuilder {
    private boolean firstValue;

    public SelectQueryBuilder() {
        this.builder = new StringBuilder("SELECT ");
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

    public SelectQueryBuilder as(String alias) {
        appendAS();
        append(alias);
        return this;
    }

    public FromQueryBuilder from(String table) {
        append(" FROM ");
        append(table);
        return new FromQueryBuilder(this.builder);
    }

    private void append(String text) {
        this.builder = this.builder.append(text);
    }

    private void appendBlank() {
        this.builder = this.builder.append(" ");
    }

    private void appendComma() {
        this.builder = this.builder.append(",");
    }

    private void appendAS() {
        this.builder = this.builder.append(" AS ");
    }
}
