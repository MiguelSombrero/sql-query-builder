package builder.statement.select.table;

import builder.query.Query;

public class SubQuery extends JoinTable {

    public SubQuery(Query query) {
        super(query);
    }

    public JoinTable alias(String alias) {
        validator.validate(alias);
        query.append(" AS ");
        query.append(alias);
        return new JoinTable(query);
    }
}
