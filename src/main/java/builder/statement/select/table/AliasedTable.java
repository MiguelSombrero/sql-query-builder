package builder.statement.select.table;

import builder.query.Query;

public class AliasedTable extends JoinTable {

    public AliasedTable(Query query) {
        super(query);
    }

    /**
     * Appends 'table' into 'FROM table(s)' statement.
     *
     * @param table Table name to be queried from
     *
     * @return Table class which can be used to append
     * tables in FROM statement, alias tables and proceed
     * to WHERE, JOIN, GROUP BY etc. statements
     */
    public Table table(String table) {
        validator.validate(table);
        query.append(", ");
        query.append(table);
        return new Table(query);
    }
}
