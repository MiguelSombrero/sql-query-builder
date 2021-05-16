package builder.statement.update;

import builder.SQLStringAppender;

import javax.xml.bind.ValidationException;

public class Table extends SQLStringAppender {

    public Table(StringBuilder queryString) {
        super(queryString);
    }

    public FirstColumn table(String tableName) throws ValidationException {
        validateAndAppend(tableName);
        append(" SET ");
        return new FirstColumn(this.queryString);
    }
}
