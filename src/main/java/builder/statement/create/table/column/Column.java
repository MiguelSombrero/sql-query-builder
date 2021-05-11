package builder.statement.create.table.column;

import builder.statement.create.table.foreignkey.ForeignKey;

public class Column extends ForeignKey {

    public Column(StringBuilder builder) {
        super(builder);
    }

    public ColumnType column(String column) {
        append(", ");
        append(column);
        return new ColumnType(this.builder);
    }
}
