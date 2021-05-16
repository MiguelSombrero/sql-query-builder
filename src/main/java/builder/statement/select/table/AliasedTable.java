package builder.statement.select.table;

import javax.xml.bind.ValidationException;

public class AliasedTable extends JoinTable {

    public AliasedTable(StringBuilder queryString) {
        super(queryString);
    }

    public Table table(String tableName) throws ValidationException {
        append(", ");
        validateAndAppend(tableName);
        return new Table(this.queryString);
    }
}
