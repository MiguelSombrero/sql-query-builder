package builder;

import builder.query.Query;

public class TerminalOperation implements QueryBuilder {

    protected Query query;

    public TerminalOperation(Query query) {
        this.query = query;
    }

    /**
     * Terminates query building.
     *
     * @return String presentation of query
     */
    public String build() {
        return query.build();
    }
}
