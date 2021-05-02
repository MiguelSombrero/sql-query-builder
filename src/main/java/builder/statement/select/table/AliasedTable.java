package builder.statement.select.table;

public class AliasedTable extends JoinTable {

    public AliasedTable(StringBuilder builder) {
        super(builder);
    }

    public Table table(String tableName) {
        append(", ");
        append(tableName);
        return new Table(this.builder);
    }
}
