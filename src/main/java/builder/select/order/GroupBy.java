package builder.select.order;

public class GroupBy extends GroupByTemplate {

    public GroupBy(StringBuilder builder) {
        super(builder);
    }

    @Override
    protected void addCommaAfterFirstValue() {
        append(", ");
    }
}
