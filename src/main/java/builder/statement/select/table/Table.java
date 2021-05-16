package builder.statement.select.table;

import javax.xml.bind.ValidationException;

public class Table extends AliasedTable {

    public Table(StringBuilder queryString) {
        super(queryString);
    }

    public AliasedTable alias(String alias) throws ValidationException {
        append(" AS ");
        validateAndAppend(alias);
        return new AliasedTable(this.queryString);
    }
}
