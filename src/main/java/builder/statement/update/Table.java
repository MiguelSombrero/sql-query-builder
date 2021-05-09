package builder.statement.update;

import builder.SQLStringBuilder;

public class Table extends SQLStringBuilder {

    public Table(StringBuilder builder) {
        super(builder);
    }

    public FirstColumn table(String tableName) {
        append(tableName);
        append(" SET ");
        return new FirstColumn(this.builder);
    }
}
