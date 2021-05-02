package builder.statement.select.table;

public class Table extends AliasedTable {

    public Table(StringBuilder builder) {
        super(builder);
    }

    public AliasedTable alias(String alias) {
        append(" AS ");
        append(alias);
        return new AliasedTable(this.builder);
    }
}
