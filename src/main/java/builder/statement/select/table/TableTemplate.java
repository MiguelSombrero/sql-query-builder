package builder.statement.select.table;

public abstract class TableTemplate extends JoinTable {

    public TableTemplate(StringBuilder builder) {
        super(builder);
    }

    public Table table(String tableName) {
        addCommaAfterFirstValue();
        append(tableName);
        return new Table(this.builder);
    }

    protected abstract void addCommaAfterFirstValue();
}
