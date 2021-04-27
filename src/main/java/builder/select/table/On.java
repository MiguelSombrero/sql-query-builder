package builder.select.table;

public class On extends AliasedOn {

    public On(StringBuilder builder) {
        super(builder);
    }

    public On alias(String alias) {
        append(" AS ");
        append(alias);
        return this;
    }
}
