package builder.statement.update;

import builder.QueryBuilder;
import query.dml.UpdateQuery;

public class TerminalUpdateOperation implements QueryBuilder {

    protected UpdateQuery query;

    public TerminalUpdateOperation(UpdateQuery query) {
        this.query = query;
    }

    /**
     * Terminates query building.
     *
     * @return UpdateQuery object which can be used to
     * execute UPDATE queries
     */
    public UpdateQuery build() {
        return this.query;
    }
}
