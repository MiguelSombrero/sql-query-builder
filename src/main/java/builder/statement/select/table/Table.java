package builder.statement.select.table;

import builder.Query;

public class Table extends AliasedTable {

    public Table(Query query) {
        super(query);
    }

    public AliasedTable alias(String alias) {
        validator.validate(alias);
        query.append(" AS ");
        query.append(alias);
        return new AliasedTable(query);
    }
}
