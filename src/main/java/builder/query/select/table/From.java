package builder.query.select.table;

import builder.query.select.SelectQueryBuilder;
import builder.appender.StringAppender;
import query.dql.SelectQuery;

public class From {
    private SelectQuery query;

    public From(SelectQuery query) {
        this.query = query;
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
        StringAppender.validateAndAppend(query, table);
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
        query.mergeClause(subQuery.build());
        query.append(")");
        return new SubQuery(query);
    }
}
