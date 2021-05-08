package builder.statement.select.table;

public class SubQuery extends JoinTable {

    public SubQuery(StringBuilder builder) {
        super(builder);
    }

    public JoinTable alias(String alias) {
        append(" AS ");
        append(alias);
        return new JoinTable(this.builder);
    }
}
