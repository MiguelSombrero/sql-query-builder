package builder.table;

public class Table extends AliasedTable {

    public Table(StringBuilder builder) {
        super(builder);
    }

    public AliasedTable alias(String alias) {
        append(" AS ");
        append(alias);
        return this;
    }
}
