package builder.delete;

import builder.SQLStringBuilder;
import builder.where.Condition;

public class Table extends SQLStringBuilder {

    public Table(StringBuilder builder) {
        this.builder = builder;
    }

    public Condition table(String tableName) {
        append(tableName);
        return new Condition(this.builder);
    }
}
