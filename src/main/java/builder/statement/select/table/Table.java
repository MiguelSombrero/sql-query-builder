package builder.statement.select.table;

import builder.Query;

public class Table extends AliasedTable {

    public Table(Query query) {
        super(query);
    }

    /**
     * Appends 'AS alias' into 'SELECT ... FROM table AS alias' statement.
     *
     * @param alias Alias name to table
     *
     * @return AliasedTable class which can be used to append
     * tables in FROM statement or proceed
     * to WHERE, JOIN, GROUP BY etc. statements
     */
    public AliasedTable alias(String alias) {
        validator.validate(alias);
        query.append(" AS ");
        query.append(alias);
        return new AliasedTable(query);
    }
}
