package builder.statement.select.table;

import query.dql.SelectQuery;

public class Table extends AliasedTable {

    public Table(SelectQuery query) {
        super(query);
    }

    /**
     * Validates user input and appends 'AS alias'
     * into 'SELECT ... FROM table AS alias' statement.
     *
     * @param alias Alias name to table
     *
     * @return AliasedTable class which can be used to append
     * tables in FROM statement or proceed
     * to WHERE, JOIN, GROUP BY etc. statements
     */
    public AliasedTable alias(String alias) {
        query.append(" AS ");
        stringAppender.validateAndAppend(alias);
        return new AliasedTable(query);
    }
}
