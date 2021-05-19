package builder.statement.create;

import builder.QueryBuilder;
import builder.query.Query;

public class TerminalCreateOperation implements QueryBuilder {

    protected Query query;

    public TerminalCreateOperation(Query query) {
        this.query = query;
    }

    public String build() {
        query.append(")");
        return query.build();
    }
}
