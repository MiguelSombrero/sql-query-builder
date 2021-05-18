package builder.statement.create.table.column;

import builder.SQLQuery;

public class ColumnType extends SQLQuery {

    public ColumnType(StringBuilder queryString) {
        super(queryString);
    }

    public Constraint type(DataType dataType) {
        append(" ");
        append(dataType.getType());
        return new Constraint(this.queryString);
    }
}
