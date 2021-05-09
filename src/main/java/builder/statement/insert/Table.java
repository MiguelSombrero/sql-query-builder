package builder.statement.insert;

import builder.SQLStringBuilder;

public class Table extends SQLStringBuilder {

    public Table(StringBuilder builder) {
        super(builder);
    }

    public Insert table(String tableName) {
        append(tableName);
        append(" ");
        return new Insert(this.builder);
    }
}
