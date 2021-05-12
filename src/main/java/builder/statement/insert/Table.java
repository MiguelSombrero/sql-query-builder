package builder.statement.insert;

import builder.SQLStringAppender;

public class Table extends SQLStringAppender {

    public Table(StringBuilder queryString) {
        super(queryString);
    }

    public Insert table(String tableName) {
        append(tableName);
        append(" ");
        return new Insert(this.queryString);
    }
}
