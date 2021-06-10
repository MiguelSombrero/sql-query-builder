package builder.statement.select.table;

import builder.appender.StringAppender;
import query.dql.SelectQuery;

public class SubQuery extends JoinTable {

    public SubQuery(SelectQuery query) {
        super(query);
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
        query.append(" AS ");
        StringAppender.validateAndAppend(query, alias);
        return new JoinTable(query);
    }
}
