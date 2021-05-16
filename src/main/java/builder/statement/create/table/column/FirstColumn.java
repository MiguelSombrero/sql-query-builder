package builder.statement.create.table.column;

import builder.SQLStringAppender;

import javax.xml.bind.ValidationException;

public class FirstColumn extends SQLStringAppender {

    public FirstColumn(StringBuilder queryString) {
        super(queryString);
    }

    public ColumnType column(String column) throws ValidationException {
        validateAndAppend(column);
        return new ColumnType(this.queryString);
    }
}
