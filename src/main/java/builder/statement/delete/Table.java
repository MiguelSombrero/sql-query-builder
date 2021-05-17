package builder.statement.delete;

import builder.SQLStringAppender;

public class Table extends SQLStringAppender {

    public Table(StringBuilder queryString) {
        super(queryString);
    }

    public Condition table(String tableName) {
        validateAndAppend(tableName);
        return new Condition(this.queryString);
    }
}
