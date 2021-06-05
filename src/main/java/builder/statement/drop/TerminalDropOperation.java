package builder.statement.drop;

import builder.QueryBuilder;
import query.ddl.DropQuery;

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
