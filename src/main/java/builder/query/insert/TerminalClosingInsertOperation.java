package builder.query.insert;

import builder.query.QueryBuilder;
import query.dml.InsertQuery;

public class TerminalClosingInsertOperation implements QueryBuilder {

    protected InsertQuery query;

    public TerminalClosingInsertOperation(InsertQuery query) {
        this.query = query;
    }

    /**
     * Closes query by appending ')' into statement.
     *
     * @return InsertQuery object which can be used to
     * execute INSERT queries
     */
    public InsertQuery build() {
        query.append(")");
        return this.query;
    }
}
