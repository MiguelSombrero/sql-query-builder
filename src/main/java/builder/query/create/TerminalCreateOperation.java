package builder.query.create;

import builder.query.QueryBuilder;
import query.ddl.CreateQuery;

public class TerminalCreateOperation implements QueryBuilder {

    protected CreateQuery query;

    public TerminalCreateOperation(CreateQuery query) {
        this.query = query;
    }

    /**
     * Closes query by appending ')' into statement.
     *
     * @return CreateQuery object which can be used to
     * execute CREATE queries
     */
    public CreateQuery build() {
        return this.query;
    }
}
