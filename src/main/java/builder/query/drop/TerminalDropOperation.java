package builder.query.drop;

import builder.query.QueryBuilder;
import builder.query.SQLQueryBuilder;
import query.Clause;
import query.DropQuery;

public class TerminalDropOperation implements QueryBuilder {

    protected Clause clause;

    public TerminalDropOperation(Clause clause) {
        this.clause = clause;
    }

    /**
     * Closes query by appending ')' into statement.
     *
     * @return DropQuery object which can be used to
     * execute CREATE queries
     */
    public DropQuery build() {
        DropQuery query = new DropQuery(clause, SQLQueryBuilder.getDataSource());
        return query;
    }
}
