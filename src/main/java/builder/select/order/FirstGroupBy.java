package builder.select.order;

public class FirstGroupBy extends GroupByTemplate {

    public FirstGroupBy(StringBuilder builder) {
        super(builder);
    }

    @Override
    protected void addCommaAfterFirstValue() {
    }
}
