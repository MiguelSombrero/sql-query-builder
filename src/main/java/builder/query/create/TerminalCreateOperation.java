package builder.query.create;

import builder.query.QueryBuilder;
import builder.query.SQLQueryBuilder;
import query.Clause;
import query.CreateQuery;

public class TerminalCreateOperation implements QueryBuilder {

    protected Clause clause;

    public TerminalCreateOperation(Clause clause) {
        this.clause = clause;
    }

    /**
     * Closes query by appending ')' into statement.
     *
     * @return CreateQuery object which can be used to
     * execute CREATE queries
     */
    public CreateQuery build() {
        CreateQuery query = new CreateQuery(clause, SQLQueryBuilder.getDataSource());
        return query;
    }
}
