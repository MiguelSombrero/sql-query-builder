package builder.query.delete;

import builder.query.QueryBuilder;
import builder.query.SQLQueryBuilder;
import query.Clause;
import query.DeleteQuery;

public class TerminalDeleteOperation implements QueryBuilder {

    protected Clause clause;

    public TerminalDeleteOperation(Clause clause) {
        this.clause = clause;
    }

    /**
     * Terminates query building.
     *
     * @return DeleteQuery object which can be used to
     * execute DELETE queries
     */
    public DeleteQuery build() {
        DeleteQuery query = new DeleteQuery(clause, SQLQueryBuilder.getDataSource());
        return query;
    }
}
