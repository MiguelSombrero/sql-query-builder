package builder.statement.select.table;

public class AliasedTable extends JoinTable {

    public AliasedTable(StringBuilder queryString) {
        super(queryString);
    }

    public Table table(String tableName) {
        append(", ");
        validateAndAppend(tableName);
        return new Table(this.queryString);
    }
}
