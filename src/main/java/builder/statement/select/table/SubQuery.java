package builder.statement.select.table;

public class SubQuery extends JoinTable {

    public SubQuery(StringBuilder queryString) {
        super(queryString);
    }

    public JoinTable alias(String alias) {
        append(" AS ");
        validateAndAppend(alias);
        return new JoinTable(this.queryString);
    }
}
