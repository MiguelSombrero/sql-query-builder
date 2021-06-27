package builder.query.insert;

import builder.query.QueryBuilder;
import builder.query.SQLQueryBuilder;
import query.Statement;
import query.InsertQuery;

public class TerminalInsertOperation implements QueryBuilder {

    protected Statement statement;

    public TerminalInsertOperation(Statement statement) {
        this.statement = statement;
    }

    /**
     * Terminates query building.
     *
     * @return DMLQuery object which can be used to
     * execute INSERT, UPDATE and DELETE queries
     */
    public InsertQuery build() {
        InsertQuery query = new InsertQuery(statement, SQLQueryBuilder.getDataSource());
        return query;
    }
}
