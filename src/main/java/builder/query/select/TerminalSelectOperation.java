package builder.query.select;

import builder.query.SQLQueryBuilder;
import query.Statement;
import query.SelectQuery;

public class TerminalSelectOperation implements SelectQueryBuilder {

    protected Statement statement;

    public TerminalSelectOperation(Statement statement) {
        this.statement = statement;
    }

    /**
     * Terminates query building.
     *
     * @return SelectQuery object which can be used to
     * execute SELECT queries
     */
    public SelectQuery build() {
        SelectQuery query = new SelectQuery(statement, SQLQueryBuilder.getDataSource());
        return query;
    }
}
