package builder.statement.insert;

import builder.SQLStringBuilder;

public class Table extends SQLStringBuilder {

    public Table(StringBuilder builder) {
        this.builder = builder;
    }

    public Insert table(String tableName) {
        append(tableName);
        append(" ");
        return new Insert(this.builder);
    }
}
