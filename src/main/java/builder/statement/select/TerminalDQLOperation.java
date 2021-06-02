package builder.statement.select;

import query.dql.DQLQuery;

public class TerminalDQLOperation implements DQLQueryBuilder {

    protected DQLQuery query;

    public TerminalDQLOperation(DQLQuery query) {
        this.query = query;
    }

    /**
     * Terminates query building.
     *
     * @return DQLQuery object which can be used to
     * execute SELECT queries
     */
    public DQLQuery build() {
        return this.query;
    }
}
