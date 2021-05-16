package builder.statement.select.table;

import javax.xml.bind.ValidationException;

public class SubQuery extends JoinTable {

    public SubQuery(StringBuilder queryString) {
        super(queryString);
    }

    public JoinTable alias(String alias) throws ValidationException {
        append(" AS ");
        validateAndAppend(alias);
        return new JoinTable(this.queryString);
    }
}
