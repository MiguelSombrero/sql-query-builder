package builder.query.update;

import builder.query.QueryBuilder;
import builder.query.SQLQueryBuilder;
import query.Statement;
import query.UpdateQuery;

public class TerminalUpdateOperation implements QueryBuilder {

    protected Statement statement;

    public TerminalUpdateOperation(Statement statement) {
        this.statement = statement;
    }

    /**
     * Terminates query building.
     *
     * @return UpdateQuery object which can be used to
     * execute UPDATE queries
     */
    public UpdateQuery build() {
        UpdateQuery query = new UpdateQuery(statement, SQLQueryBuilder.getDataSource());
        return query;
    }
}
