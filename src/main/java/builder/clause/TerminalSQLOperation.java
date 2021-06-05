package builder.clause;

import builder.QueryBuilder;
import query.SQLQuery;

public class TerminalSQLOperation implements QueryBuilder {

    protected SQLQuery query;

    public TerminalSQLOperation(SQLQuery query) {
        this.query = query;
    }

    /**
     * Terminates query building.
     *
     * @return SQLQuery object which can be used to
     * execute SQL queries
     */
    public SQLQuery build() {
        return this.query;
    }
}
