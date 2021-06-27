package builder.query.insert;

import builder.query.QueryBuilder;
import builder.query.SQLQueryBuilder;
import query.Statement;
import query.InsertQuery;

public class TerminalClosingInsertOperation implements QueryBuilder {

    protected Statement statement;

    public TerminalClosingInsertOperation(Statement statement) {
        this.statement = statement;
    }

    /**
     * Closes query by appending ')' into statement.
     *
     * @return InsertQuery object which can be used to
     * execute INSERT queries
     */
    public InsertQuery build() {
        statement.append(")");
        InsertQuery query = new InsertQuery(statement, SQLQueryBuilder.getDataSource());
        return query;
    }
}
