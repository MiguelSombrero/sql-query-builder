package builder;

import query.SQLQuery;

public class TerminalOperation implements QueryBuilder {

    protected SQLQuery SQLQuery;

    public TerminalOperation(SQLQuery SQLQuery) {
        this.SQLQuery = SQLQuery;
    }

    /**
     * Terminates query building.
     *
     * @return String presentation of query
     */
    public String build() {
        return SQLQuery.build();
    }
}
