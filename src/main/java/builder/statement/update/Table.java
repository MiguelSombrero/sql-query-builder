package builder.statement.update;

import builder.SQLStringAppender;

public class Table extends SQLStringAppender {

    public Table(StringBuilder queryString) {
        super(queryString);
    }

    public FirstColumn table(String tableName) {
        validateAndAppend(tableName);
        append(" SET ");
        return new FirstColumn(this.queryString);
    }
}
