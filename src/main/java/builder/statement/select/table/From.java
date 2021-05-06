package builder.statement.select.table;

import builder.Builder;
import builder.SQLStringBuilder;

public class From extends SQLStringBuilder {

    public From(StringBuilder builder) {
        this.builder = builder;
    }

    public Table table(String tableName) {
        append(tableName);
        return new Table(this.builder);
    }

    public SubQuery sub(Builder query) {
        append("(");
        append(query.build());
        append(")");
        return new SubQuery(this.builder);
    }
}
