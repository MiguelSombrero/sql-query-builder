package builder.statement.select.table;

import javax.xml.bind.ValidationException;

public class On extends AliasedOn {

    public On(StringBuilder queryString) {
        super(queryString);
    }

    public On alias(String alias) throws ValidationException {
        append(" AS ");
        validateAndAppend(alias);
        return this;
    }
}
