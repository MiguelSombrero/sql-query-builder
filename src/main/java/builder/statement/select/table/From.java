package builder.statement.select.table;

import builder.SQLStringAppender;
import builder.statement.select.SelectBuilder;

public class From extends SQLStringAppender {

    public From(StringBuilder queryString) {
        super(queryString);
    }

    public Table table(String tableName) {
        validateAndAppend(tableName);
        return new Table(this.queryString);
    }

    public SubQuery sub(SelectBuilder query) {
        append("(");
        append(query.build());
        append(")");
        return new SubQuery(this.queryString);
    }
}
