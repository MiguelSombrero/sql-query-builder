package builder.query.drop;

import builder.query.QueryBuilder;
import query.DropQuery;

public class TerminalDropOperation implements QueryBuilder {

    protected DropQuery query;

    public TerminalDropOperation(DropQuery query) {
        this.query = query;
    }

    /**
     * Closes query by appending ')' into statement.
     *
     * @return DropQuery object which can be used to
     * execute CREATE queries
     */
    public DropQuery build() {
        return this.query;
    }
}
