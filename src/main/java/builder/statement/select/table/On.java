package builder.statement.select.table;

import builder.Query;

public class On extends AliasedOn {

    public On(Query query) {
        super(query);
    }

    public On alias(String alias) {
        validator.validate(alias);
        query.append(" AS ");
        query.append(alias);
        return this;
    }
}
