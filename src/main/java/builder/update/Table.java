package builder.update;

import builder.SQLStringBuilder;

public class Table extends SQLStringBuilder {

    public Table(StringBuilder builder) {
        this.builder = builder;
    }

    public FirstColumn table(String tableName) {
        append(tableName);
        append(" SET ");
        return new FirstColumn(this.builder);
    }
}
