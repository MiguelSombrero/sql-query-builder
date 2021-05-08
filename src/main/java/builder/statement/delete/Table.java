package builder.statement.delete;

import builder.SQLStringBuilder;

public class Table extends SQLStringBuilder {

    public Table(StringBuilder builder) {
        this.builder = builder;
    }

    public Condition table(String tableName) {
        append(tableName);
        return new Condition(this.builder);
    }
}
