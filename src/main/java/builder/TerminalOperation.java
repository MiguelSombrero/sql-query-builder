package builder;

import query.SQLQuery;

public class TerminalOperation implements QueryBuilder {

    protected SQLQuery query;

    public TerminalOperation(SQLQuery query) {
        this.query = query;
    }

    /**
     * Terminates query building.
     *
     * @return SQLQuery object which can be used to
     * execute SQL queries
     */
    public SQLQuery build() {
        return this.query;
    }
}
