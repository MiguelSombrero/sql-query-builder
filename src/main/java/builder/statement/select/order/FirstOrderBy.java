package builder.statement.select.order;

public class FirstOrderBy extends OrderByTemplate {

    public FirstOrderBy(StringBuilder builder) {
        super(builder);
    }

    @Override
    protected void addCommaAfterFirstValue() {
    }
}
