package builder;

import query.DMLQuery;

public class TerminalDMLOperation implements QueryBuilder {

    protected DMLQuery query;

    public TerminalDMLOperation(DMLQuery query) {
        this.query = query;
    }

    /**
     * Terminates query building.
     *
     * @return DMLQuery object which can be used to
     * execute INSERT, UPDATE and DELETE queries
     */
    public DMLQuery build() {
        return this.query;
    }
}
