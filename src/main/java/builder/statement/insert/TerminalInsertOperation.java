package builder.statement.insert;

import builder.Builder;
import builder.Query;

public class TerminalInsertOperation implements Builder {

    protected Query query;

    public TerminalInsertOperation(Query query) {
        this.query = query;
    }

    public String build() {
        query.append(")");
        return query.build();
    }
}
