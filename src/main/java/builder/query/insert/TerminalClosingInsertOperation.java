package builder.query.insert;

import builder.query.QueryBuilder;
import builder.query.SQLQueryBuilder;
import query.Clause;
import query.InsertQuery;

public class TerminalClosingInsertOperation implements QueryBuilder {

    protected Clause clause;

    public TerminalClosingInsertOperation(Clause clause) {
        this.clause = clause;
    }

    /**
     * Closes query by appending ')' into statement.
     *
     * @return InsertQuery object which can be used to
     * execute INSERT queries
     */
    public InsertQuery build() {
        clause.append(")");
        InsertQuery query = new InsertQuery(clause, SQLQueryBuilder.getDataSource());
        return query;
    }
}
