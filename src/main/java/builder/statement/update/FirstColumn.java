package builder.statement.update;

import builder.SQLStringAppender;

import javax.xml.bind.ValidationException;

public class FirstColumn extends SQLStringAppender {

    public FirstColumn(StringBuilder queryString) {
        super(queryString);
    }

    public Value column(String column) throws ValidationException {
        validateAndAppend(column);
        append(" = ");
        return new Value(this.queryString);
    }
}
