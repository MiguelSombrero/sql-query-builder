package builder.query.insert;

import builder.query.QueryBuilder;
import query.dml.InsertQuery;

public class TerminalInsertOperation implements QueryBuilder {

    protected InsertQuery query;

    public TerminalInsertOperation(InsertQuery query) {
        this.query = query;
    }

    /**
     * Terminates query building.
     *
     * @return DMLQuery object which can be used to
     * execute INSERT, UPDATE and DELETE queries
     */
    public InsertQuery build() {
        return this.query;
    }
}
