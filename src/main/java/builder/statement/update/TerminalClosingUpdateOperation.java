package builder.statement.update;

import builder.QueryBuilder;
import query.dml.UpdateQuery;

public class TerminalClosingUpdateOperation implements QueryBuilder {

    protected UpdateQuery query;

    public TerminalClosingUpdateOperation(UpdateQuery query) {
        this.query = query;
    }

    /**
     * Closes query by appending ')' into statement.
     *
     * @return UpdateQuery object which can be used to
     * execute UPDATE queries
     */
    public UpdateQuery build() {
        query.append(")");
        return this.query;
    }
}
