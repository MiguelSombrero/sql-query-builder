package builder.statement.create;

import builder.QueryBuilder;
import query.ddl.CreateQuery;

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
