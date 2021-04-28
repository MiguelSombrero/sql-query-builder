package builder.select.column;

public class Column extends AliasedColumn {

    public Column(StringBuilder builder) {
        super(builder);
    }

    public AliasedColumn alias(String text) {
        append(" AS ");
        append(text);
        return new AliasedColumn(this.builder);
    }
}
