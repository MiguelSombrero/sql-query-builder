package builder.statement.create;

import builder.QueryBuilder;
import query.DDLQuery;

public class TerminalCreateOperation implements QueryBuilder {

    protected DDLQuery query;

    public TerminalCreateOperation(DDLQuery query) {
        this.query = query;
    }

    /**
     * Closes query by appending ')' into statement.
     *
     * @return DDLQuery object which can be used to
     * execute CREATE queries
     */
    public DDLQuery build() {
        query.append(")");
        return this.query;
    }
}
