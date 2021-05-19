package builder.statement.create.table.column;

import builder.Query;

public class ColumnType extends Query {

    public ColumnType(StringBuilder queryString) {
        super(queryString);
    }

    public Constraint type(DataType dataType) {
        append(" ");
        append(dataType.getType());
        return new Constraint(this.queryString);
    }
}
