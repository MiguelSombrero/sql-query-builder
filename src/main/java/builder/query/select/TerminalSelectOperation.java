package builder.query.select;

import builder.query.SQLQueryBuilder;
import query.Clause;
import query.SelectQuery;

public class TerminalSelectOperation implements SelectQueryBuilder {

    protected Clause clause;

    public TerminalSelectOperation(Clause clause) {
        this.clause = clause;
    }

    /**
     * Terminates query building.
     *
     * @return SelectQuery object which can be used to
     * execute SELECT queries
     */
    public SelectQuery build() {
        SelectQuery query = new SelectQuery(clause, SQLQueryBuilder.getDataSource());
        return query;
    }
}
