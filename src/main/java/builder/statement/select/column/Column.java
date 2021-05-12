package builder.statement.select.column;

public class Column extends AliasedColumn {

    public Column(StringBuilder queryString) {
        super(queryString);
    }

    public AliasedColumn alias(String text) {
        append(" AS ");
        append(text);
        return new AliasedColumn(this.queryString);
    }
}
