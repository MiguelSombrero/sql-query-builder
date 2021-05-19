package builder.statement.insert;

import builder.query.Query;
import builder.TerminalOperation;
import builder.statement.select.SelectQueryBuilder;

public class Column {

    protected Query query;

    public Column(Query query) {
        this.query = query;
    }

    public FirstValue values() {
        query.append("VALUES (");
        return new FirstValue(query);
    }

    public TerminalOperation sub(SelectQueryBuilder subQuery) {
        query.append(subQuery.build());
        return new TerminalOperation(query);
    }
}
