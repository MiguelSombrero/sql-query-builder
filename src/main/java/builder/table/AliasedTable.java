package builder.table;

public class AliasedTable extends JoinTable {

    public AliasedTable(StringBuilder builder) {
        super(builder);
    }

    public Table and(String table) {
        append(", ");
        append(table);
        return new Table(this.builder);
    }
}
