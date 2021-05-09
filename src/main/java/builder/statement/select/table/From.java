package builder.statement.select.table;

import builder.SQLStringBuilder;
import builder.statement.select.SelectBuilder;

public class From extends SQLStringBuilder {

    public From(StringBuilder builder) {
        super(builder);
    }

    public Table table(String tableName) {
        append(tableName);
        return new Table(this.builder);
    }

    public SubQuery sub(SelectBuilder query) {
        append("(");
        append(query.build());
        append(")");
        return new SubQuery(this.builder);
    }
}
