package builder.query.update;

import builder.query.QueryBuilder;
import builder.query.SQLQueryBuilder;
import query.Clause;
import query.UpdateQuery;

public class TerminalUpdateOperation implements QueryBuilder {

    protected Clause clause;

    public TerminalUpdateOperation(Clause clause) {
        this.clause = clause;
    }

    /**
     * Terminates query building.
     *
     * @return UpdateQuery object which can be used to
     * execute UPDATE queries
     */
    public UpdateQuery build() {
        UpdateQuery query = new UpdateQuery(clause, SQLQueryBuilder.getDataSource());
        return query;
    }
}
