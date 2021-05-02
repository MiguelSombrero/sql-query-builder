package builder.statement.select.table;

import builder.SQLStringBuilder;

public class FirstTable extends SQLStringBuilder {

    public FirstTable(StringBuilder builder) {
        this.builder = builder;
    }

    public Table table(String tableName) {
        append(tableName);
        return new Table(this.builder);
    }
}
