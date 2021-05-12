package builder.statement.create.table.column;

import builder.SQLStringAppender;

public class ColumnType extends SQLStringAppender {

    public ColumnType(StringBuilder queryString) {
        super(queryString);
    }

    public Constraint type(DataType dataType) {
        append(" ");
        append(dataType.getType());
        return new Constraint(this.queryString);
    }
}
