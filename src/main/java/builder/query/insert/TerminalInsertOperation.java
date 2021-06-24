package builder.query.insert;

import builder.query.QueryBuilder;
import builder.query.SQLQueryBuilder;
import query.Clause;
import query.InsertQuery;

public class TerminalInsertOperation implements QueryBuilder {

    protected Clause clause;

    public TerminalInsertOperation(Clause clause) {
        this.clause = clause;
    }

    /**
     * Terminates query building.
     *
     * @return DMLQuery object which can be used to
     * execute INSERT, UPDATE and DELETE queries
     */
    public InsertQuery build() {
        InsertQuery query = new InsertQuery(clause, SQLQueryBuilder.getDataSource());
        return query;
    }
}
