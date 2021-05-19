package builder.statement.create;

import builder.Builder;
import builder.Query;

public class TerminalCreateOperation implements Builder {

    protected Query query;

    public TerminalCreateOperation(Query query) {
        this.query = query;
    }

    public String build() {
        query.append(")");
        return query.build();
    }
}
