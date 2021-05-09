package builder.statement.delete;

import builder.SQLStringBuilder;

public class Table extends SQLStringBuilder {

    public Table(StringBuilder builder) {
        super(builder);
    }

    public Condition table(String tableName) {
        append(tableName);
        return new Condition(this.builder);
    }
}
