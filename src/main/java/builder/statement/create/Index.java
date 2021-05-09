package builder.statement.create;

import builder.SQLStringBuilder;

public class Index extends SQLStringBuilder {

    public Index(StringBuilder builder) {
        super(builder);
    }

    public IndexedColumn on(String table) {
        append(" ON ");
        append(table);
        return new IndexedColumn(this.builder);
    }
}
