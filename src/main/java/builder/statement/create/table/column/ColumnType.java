package builder.statement.create.table.column;

import builder.SQLStringBuilder;

public class ColumnType extends SQLStringBuilder {

    public ColumnType(StringBuilder builder) {
        super(builder);
    }

    public Constraint type(DataType dataType) {
        append(" ");
        append(dataType.getType());
        return new Constraint(this.builder);
    }
}
