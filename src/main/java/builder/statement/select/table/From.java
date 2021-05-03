package builder.statement.select.table;

import builder.SQLStringBuilder;

public class From extends SQLStringBuilder {

    public From(StringBuilder builder) {
        this.builder = builder;
    }

    public Table table(String tableName) {
        append(tableName);
        return new Table(this.builder);
    }

    public SubQuery sub(String query) {
        append("(");
        append(query);
        append(")");
        return new SubQuery(this.builder);
    }
}
