package builder.statement.insert;

import builder.SQLStringAppender;

public class Table extends SQLStringAppender {

    public Table(StringBuilder queryString) {
        super(queryString);
    }

    public Insert table(String tableName) {
        validateAndAppend(tableName);
        append(" ");
        return new Insert(this.queryString);
    }
}
