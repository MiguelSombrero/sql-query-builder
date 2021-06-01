package builder.statement.insert;

import builder.QueryBuilder;
import query.DMLQuery;

public class TerminalInsertOperation implements QueryBuilder {

    protected DMLQuery query;

    public TerminalInsertOperation(DMLQuery query) {
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
