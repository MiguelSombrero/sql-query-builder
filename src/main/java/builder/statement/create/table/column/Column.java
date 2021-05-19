package builder.statement.create.table.column;

import builder.query.Query;
import builder.statement.create.table.foreignkey.ForeignKey;

public class Column extends ForeignKey {

    public Column(Query query) {
        super(query);
    }

    public ColumnType column(String column) {
        validator.validate(column);
        query.append(", ");
        query.append(column);
        return new ColumnType(query);
    }
}
