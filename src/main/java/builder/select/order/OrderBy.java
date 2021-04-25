package builder.select.order;

public class OrderBy extends OrderByTemplate {

    public OrderBy(StringBuilder builder) {
        super(builder);
    }

    @Override
    protected void addCommaAfterFirstValue() {
        append(", ");
    }
}
