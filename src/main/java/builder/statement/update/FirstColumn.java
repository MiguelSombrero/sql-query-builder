package builder.statement.update;

import builder.SQLStringBuilder;

public class FirstColumn extends SQLStringBuilder {

    public FirstColumn(StringBuilder builder) {
        this.builder = builder;
    }

    public Value column(String column) {
        append(column);
        append(" = ");
        return new Value(this.builder);
    }
}
