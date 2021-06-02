package builder;

import query.ddl.DDLQuery;

public class TerminalDDLOperation implements QueryBuilder {

    protected DDLQuery query;

    public TerminalDDLOperation(DDLQuery query) {
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
