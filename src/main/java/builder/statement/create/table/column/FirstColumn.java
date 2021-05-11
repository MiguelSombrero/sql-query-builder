package builder.statement.create.table.column;

import builder.SQLStringBuilder;

public class FirstColumn extends SQLStringBuilder {

    public FirstColumn(StringBuilder builder) {
        super(builder);
    }

    public ColumnType column(String column) {
        append(column);
        return new ColumnType(this.builder);
    }
}
