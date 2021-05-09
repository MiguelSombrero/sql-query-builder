package builder.statement.update;

import builder.SQLStringBuilder;

public class FirstColumn extends SQLStringBuilder {

    public FirstColumn(StringBuilder builder) {
        super(builder);
    }

    public Value column(String column) {
        append(column);
        append(" = ");
        return new Value(this.builder);
    }
}
