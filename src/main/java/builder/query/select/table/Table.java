package builder.query.select.table;

import builder.appender.StringAppender;
import query.Clause;

public class Table extends AliasedTable {

    public Table(Clause clause) {
        super(clause);
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
        clause.append(" AS ");
        StringAppender.validateAndAppend(clause, alias);
        return new AliasedTable(clause);
    }
}
