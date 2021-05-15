package builder.statement.delete;

import builder.SQLStringAppender;

import javax.xml.bind.ValidationException;

public class Table extends SQLStringAppender {

    public Table(StringBuilder queryString) {
        super(queryString);
    }

    public Condition table(String tableName) throws ValidationException {
        validateAndAppend(tableName);
        return new Condition(this.queryString);
    }
}
