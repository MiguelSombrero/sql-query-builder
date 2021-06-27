package builder.query.drop;

import builder.query.QueryBuilder;
import builder.query.SQLQueryBuilder;
import query.Statement;
import query.DropQuery;

public class TerminalDropOperation implements QueryBuilder {

    protected Statement statement;

    public TerminalDropOperation(Statement statement) {
        this.statement = statement;
    }

    /**
     * Closes query by appending ')' into statement.
     *
     * @return DropQuery object which can be used to
     * execute CREATE queries
     */
    public DropQuery build() {
        DropQuery query = new DropQuery(statement, SQLQueryBuilder.getDataSource());
        return query;
    }
}
