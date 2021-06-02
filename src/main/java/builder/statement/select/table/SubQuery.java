package builder.statement.select.table;

import query.dql.DQLQuery;

public class SubQuery extends JoinTable {

    public SubQuery(DQLQuery query) {
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
        stringAppender.validateAndAppend(alias);
        return new JoinTable(query);
    }
}
