package builder.statement.insert;

import builder.SQLQuery;
import builder.TerminalOperation;
import builder.statement.select.SelectBuilder;

public class Column extends SQLQuery {

    public Column(StringBuilder queryString) {
        super(queryString);
    }

    public FirstValue values() {
        append("VALUES (");
        return new FirstValue(this.queryString);
    }

    public TerminalOperation sub(SelectBuilder query) {
        append(query.build());
        return new TerminalOperation(this.queryString);
    }
}
