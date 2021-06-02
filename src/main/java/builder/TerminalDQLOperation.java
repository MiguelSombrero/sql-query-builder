package builder;

import query.dql.DQLQuery;

public class TerminalDQLOperation implements QueryBuilder {

    protected DQLQuery query;

    public TerminalDQLOperation(DQLQuery query) {
        this.query = query;
    }

    /**
     * Closes query building.
     *
     * @return DDLQuery object which can be used to
     * execute CREATE queries
     */
    public DQLQuery build() {
        return this.query;
    }
}
