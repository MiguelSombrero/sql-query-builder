package builder.statement.select.table;

public class On extends AliasedOn {

    public On(StringBuilder queryString) {
        super(queryString);
    }

    public On alias(String alias) {
        validator.validate(alias);
        append(" AS ");
        append(alias);
        return this;
    }
}
