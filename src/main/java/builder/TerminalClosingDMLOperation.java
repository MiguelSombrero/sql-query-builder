package builder;

import query.dml.DMLQuery;

public class TerminalClosingDMLOperation implements QueryBuilder {

    protected DMLQuery query;

    public TerminalClosingDMLOperation(DMLQuery query) {
        this.query = query;
    }

    /**
     * Closes query by appending ')' into statement.
     *
     * @return InsertQuery object which can be used to
     * execute INSERT queries
     */
    public DMLQuery build() {
        query.append(")");
        return this.query;
    }
}
