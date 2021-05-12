package builder.statement.update;

import builder.SQLStringAppender;

public class FirstColumn extends SQLStringAppender {

    public FirstColumn(StringBuilder queryString) {
        super(queryString);
    }

    public Value column(String column) {
        append(column);
        append(" = ");
        return new Value(this.queryString);
    }
}
