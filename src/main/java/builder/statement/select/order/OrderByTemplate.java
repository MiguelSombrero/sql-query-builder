package builder.statement.select.order;

public abstract class OrderByTemplate extends Limiter {

    public OrderByTemplate(StringBuilder builder) {
        super(builder);
    }

    public Order column(String columnName) {
        addCommaAfterFirstValue();
        append(columnName);
        return new Order(this.builder);
    }

    protected abstract void addCommaAfterFirstValue();
}
