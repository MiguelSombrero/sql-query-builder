package builder.query.create;

import builder.query.QueryBuilder;
import builder.query.SQLQueryBuilder;
import query.Statement;
import query.CreateQuery;

public class TerminalClosingCreateOperation implements QueryBuilder {

    protected Statement statement;

    public TerminalClosingCreateOperation(Statement statement) {
        this.statement = statement;
    }

    /**
     * Closes query by appending ')' into statement.
     *
     * @return CreateQuery object which can be used to
     * execute CREATE queries
     */
    public CreateQuery build() {
        statement.append(")");
        CreateQuery query = new CreateQuery(statement, SQLQueryBuilder.getDataSource());
        return query;
    }
}
