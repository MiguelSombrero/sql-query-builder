package builder.query.delete;

import builder.query.QueryBuilder;
import query.dml.DeleteQuery;

public class TerminalDeleteOperation implements QueryBuilder {

    protected DeleteQuery query;

    public TerminalDeleteOperation(DeleteQuery query) {
        this.query = query;
    }

    /**
     * Terminates query building.
     *
     * @return DeleteQuery object which can be used to
     * execute DELETE queries
     */
    public DeleteQuery build() {
        return this.query;
    }
}
