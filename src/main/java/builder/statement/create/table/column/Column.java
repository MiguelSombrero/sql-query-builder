package builder.statement.create.table.column;

import builder.statement.create.table.foreignkey.ForeignKey;

import javax.xml.bind.ValidationException;

public class Column extends ForeignKey {

    public Column(StringBuilder queryString) {
        super(queryString);
    }

    public ColumnType column(String column) throws ValidationException {
        append(", ");
        validateAndAppend(column);
        return new ColumnType(this.queryString);
    }
}
