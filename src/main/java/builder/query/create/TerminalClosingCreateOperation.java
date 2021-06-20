package builder.query.create;

import builder.query.QueryBuilder;
import query.CreateQuery;

public class TerminalClosingCreateOperation implements QueryBuilder {

    protected CreateQuery query;

    public TerminalClosingCreateOperation(CreateQuery query) {
        this.query = query;
    }

    /**
     * Closes query by appending ')' into statement.
     *
     * @return CreateQuery object which can be used to
     * execute CREATE queries
     */
    public CreateQuery build() {
        query.append(")");
        return this.query;
    }
}
