package builder.statement.insert;

import builder.QueryBuilder;
import query.SQLQuery;

public class TerminalInsertOperation implements QueryBuilder {

    protected SQLQuery SQLQuery;

    public TerminalInsertOperation(SQLQuery SQLQuery) {
        this.SQLQuery = SQLQuery;
    }

    /**
     * Closes query by appending ')' into statement.
     *
     * @return String presentation of a query
     * that was build
     */
    public String build() {
        SQLQuery.append(")");
        return SQLQuery.build();
    }
}
