package builder.statement.select.table;

import builder.Query;

public class SubQuery extends JoinTable {

    public SubQuery(Query query) {
        super(query);
    }

    /**
     * Appends 'AS alias' into 'SELECT ... FROM (SELECT ...) AS alias' statement.
     *
     * @param alias Alias name to sub-query
     *
     * @return JoinTable class which can be used to create
     * more joins or proceed to WHERE, GROUP BY, etc. clauses
     */
    public JoinTable alias(String alias) {
        validator.validate(alias);
        query.append(" AS ");
        query.append(alias);
        return new JoinTable(query);
    }
}
