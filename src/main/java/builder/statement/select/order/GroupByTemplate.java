package builder.statement.select.order;

public abstract class GroupByTemplate extends Orderer {

    public GroupByTemplate(StringBuilder builder) {
        super(builder);
    }

    public GroupBy column(String columnName) {
        addCommaAfterFirstValue();
        append(columnName);
        return new GroupBy(this.builder);
    }

    protected abstract void addCommaAfterFirstValue();
}
