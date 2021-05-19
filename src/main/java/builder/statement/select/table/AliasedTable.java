package builder.statement.select.table;

import builder.Query;

public class AliasedTable extends JoinTable {

    public AliasedTable(Query query) {
        super(query);
    }

    public Table table(String table) {
        validator.validate(table);
        query.append(", ");
        query.append(table);
        return new Table(query);
    }
}
