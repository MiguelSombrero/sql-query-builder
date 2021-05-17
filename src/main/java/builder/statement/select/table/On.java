package builder.statement.select.table;

public class On extends AliasedOn {

    public On(StringBuilder queryString) {
        super(queryString);
    }

    public On alias(String alias) {
        append(" AS ");
        validateAndAppend(alias);
        return this;
    }
}
