package builder.statement.create.table.column;

import builder.Query;

public class ColumnType {

    private Query query;

    public ColumnType(Query query) {
        this.query = query;
    }

    public Constraint type(DataType dataType) {
        query.append(" ");
        query.append(dataType.getType());
        return new Constraint(query);
    }
}
