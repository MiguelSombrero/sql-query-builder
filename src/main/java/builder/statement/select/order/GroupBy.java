package builder.statement.select.order;

public class GroupBy extends GroupByTemplate {

    public GroupBy(StringBuilder builder) {
        super(builder);
    }

    public Orderer having(String condition) {
        append(" HAVING ");
        append(condition);
        return new Orderer(this.builder);
    }

    @Override
    protected void addCommaAfterFirstValue() {
        append(", ");
    }
}
