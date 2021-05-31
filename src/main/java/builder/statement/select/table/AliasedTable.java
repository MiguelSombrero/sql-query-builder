package builder.statement.select.table;

import query.SelectQuery;

public class AliasedTable extends JoinTable {

    public AliasedTable(SelectQuery query) {
        super(query);
    }

    /**
     * Validates user input and appends
     * 'table' into 'FROM table(s)' statement.
     *
     * @param table Table name to be queried from
     *
     * @return Table class which can be used to append
     * tables in FROM statement, alias tables and proceed
     * to WHERE, JOIN, GROUP BY etc. statements
     */
    public Table table(String table) {
        query.append(", ");
        stringAppender.validateAndAppend(table);
        return new Table(query);
    }
}
