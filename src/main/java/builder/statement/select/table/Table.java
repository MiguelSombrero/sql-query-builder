package builder.statement.select.table;

public class Table extends AliasedTable {

    public Table(StringBuilder queryString) {
        super(queryString);
    }

    public AliasedTable alias(String alias) {
        append(" AS ");
        validateAndAppend(alias);
        return new AliasedTable(this.queryString);
    }
}
