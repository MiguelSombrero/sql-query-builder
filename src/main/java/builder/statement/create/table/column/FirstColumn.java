package builder.statement.create.table.column;

import builder.SQLStringAppender;

public class FirstColumn extends SQLStringAppender {

    public FirstColumn(StringBuilder queryString) {
        super(queryString);
    }

    public ColumnType column(String column) {
        validateAndAppend(column);
        return new ColumnType(this.queryString);
    }
}
