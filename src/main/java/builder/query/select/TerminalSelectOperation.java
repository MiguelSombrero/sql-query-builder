package builder.query.select;

import query.dql.SelectQuery;

public class TerminalSelectOperation implements SelectQueryBuilder {

    protected SelectQuery query;

    public TerminalSelectOperation(SelectQuery query) {
        this.query = query;
    }

    /**
     * Terminates query building.
     *
     * @return DQLQuery object which can be used to
     * execute SELECT queries
     */
    public SelectQuery build() {
        return this.query;
    }
}
