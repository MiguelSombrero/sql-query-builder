package builder.query.select.table;

import builder.appender.StringAppender;
import query.Clause;

public class AliasedTable extends JoinTable {

    public AliasedTable(Clause clause) {
        super(clause);
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
        clause.append(", ");
        StringAppender.validateAndAppend(clause, table);
        return new Table(clause);
    }
}
