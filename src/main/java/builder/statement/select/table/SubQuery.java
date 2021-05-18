package builder.statement.select.table;

public class SubQuery extends JoinTable {

    public SubQuery(StringBuilder queryString) {
        super(queryString);
    }

    public JoinTable alias(String alias) {
        validator.validate(alias);
        append(" AS ");
        append(alias);
        return new JoinTable(this.queryString);
    }
}
