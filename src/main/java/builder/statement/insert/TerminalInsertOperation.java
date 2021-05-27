package builder.statement.insert;

import builder.QueryBuilder;
import builder.Query;

public class TerminalInsertOperation implements QueryBuilder {

    protected Query query;

    public TerminalInsertOperation(Query query) {
        this.query = query;
    }

    /**
     * Closes query by appending ')' into statement.
     *
     * @return String presentation of a query
     * that was build
     */
    public String build() {
        query.append(")");
        return query.build();
    }
}
