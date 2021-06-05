package builder.statement.select.table;

import builder.statement.select.SelectQueryBuilder;
import builder.utils.StringAppender;
import query.dql.SelectQuery;

public class From {
    protected StringAppender stringAppender;

    private SelectQuery query;

    public From(SelectQuery query) {
        this.query = query;
        this.stringAppender = new StringAppender(query);
    }

    /**
     * Validates user input and appends
     * 'table' into 'FROM table' statement.
     *
     * @param table Table name to be queried from
     *
     * @return Table class which can be used to append
     * tables in FROM statement, alias tables and proceed
     * to WHERE, JOIN, GROUP BY etc. statements
     */
    public Table table(String table) {
        stringAppender.validateAndAppend(table);
        return new Table(query);
    }

    /**
     * Appends sub-select in 'SELECT ... FROM (SELECT ...)' statement.
     * Sub-select is created with factory class QueryFactory
     *
     * @param subQuery Sub-select to be inserted in FROM statement
     *
     * @return SubQuery class which can be used to
     * alias sub-query or proceed to WHERE and JOIN statements
     */
    public SubQuery sub(SelectQueryBuilder subQuery) {
        query.append("(");
        query.mergeSubQuery(subQuery.build());
        query.append(")");
        return new SubQuery(query);
    }
}
