package builder.statement.update;

import builder.SQLStringAppender;

public class FirstColumn extends SQLStringAppender {

    public FirstColumn(StringBuilder queryString) {
        super(queryString);
    }

    public Value column(String column) {
        validateAndAppend(column);
        append(" = ");
        return new Value(this.queryString);
    }
}
