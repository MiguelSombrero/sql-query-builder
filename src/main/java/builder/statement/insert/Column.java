package builder.statement.insert;

import builder.Query;
import builder.TerminalOperation;
import builder.statement.select.SelectBuilder;

public class Column {

    protected Query query;

    public Column(Query query) {
        this.query = query;
    }

    public FirstValue values() {
        query.append("VALUES (");
        return new FirstValue(query);
    }

    public TerminalOperation sub(SelectBuilder subQuery) {
        query.append(subQuery.build());
        return new TerminalOperation(query);
    }
}
