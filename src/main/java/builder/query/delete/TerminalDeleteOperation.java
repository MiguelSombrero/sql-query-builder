package builder.query.delete;

import builder.query.QueryBuilder;
import builder.query.SQLQueryBuilder;
import query.Statement;
import query.DeleteQuery;

public class TerminalDeleteOperation implements QueryBuilder {

    protected Statement statement;

    public TerminalDeleteOperation(Statement statement) {
        this.statement = statement;
    }

    /**
     * Terminates query building.
     *
     * @return DeleteQuery object which can be used to
     * execute DELETE queries
     */
    public DeleteQuery build() {
        DeleteQuery query = new DeleteQuery(statement, SQLQueryBuilder.getDataSource());
        return query;
    }
}
