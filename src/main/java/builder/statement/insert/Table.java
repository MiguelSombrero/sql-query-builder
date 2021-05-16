package builder.statement.insert;

import builder.SQLStringAppender;

import javax.xml.bind.ValidationException;

public class Table extends SQLStringAppender {

    public Table(StringBuilder queryString) {
        super(queryString);
    }

    public Insert table(String tableName) throws ValidationException {
        validateAndAppend(tableName);
        append(" ");
        return new Insert(this.queryString);
    }
}
