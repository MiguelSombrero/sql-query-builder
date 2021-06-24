package builder.query.select.table;

import builder.appender.StringAppender;
import query.Clause;

public class SubQuery extends JoinTable {

    public SubQuery(Clause clause) {
        super(clause);
    }

    /**
     * Validates user input and appends 'AS alias'
     * into 'SELECT ... FROM (SELECT ...) AS alias' statement.
     *
     * @param alias Alias name to sub-query
     *
     * @return JoinTable class which can be used to create
     * more joins or proceed to WHERE, GROUP BY, etc. clauses
     */
    public JoinTable alias(String alias) {
        clause.append(" AS ");
        StringAppender.validateAndAppend(clause, alias);
        return new JoinTable(clause);
    }
}
