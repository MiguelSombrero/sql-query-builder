package builder.query.select.table;

import builder.appender.StringAppender;
import query.Statement;

public class SubQuery extends JoinTable {

    public SubQuery(Statement statement) {
        super(statement);
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
        statement.append(" AS ");
        StringAppender.validateAndAppend(statement, alias);
        return new JoinTable(statement);
    }
}
