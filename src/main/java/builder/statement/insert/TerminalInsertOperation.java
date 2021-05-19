package builder.statement.insert;

import builder.QueryBuilder;
import builder.query.Query;

public class TerminalInsertOperation implements QueryBuilder {

    protected Query query;

    public TerminalInsertOperation(Query query) {
        this.query = query;
    }

    public String build() {
        query.append(")");
        return query.build();
    }
}
