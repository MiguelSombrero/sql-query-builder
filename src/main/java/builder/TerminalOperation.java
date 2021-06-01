package builder;

import query.Query;

public class TerminalOperation implements QueryBuilder {

    protected Query query;

    public TerminalOperation(Query query) {
        this.query = query;
    }

    /**
     * Terminates query building.
     *
     * @return Query object which can be used to
     * execute SQL queries
     */
    public Query build() {
        return this.query;
    }
}
