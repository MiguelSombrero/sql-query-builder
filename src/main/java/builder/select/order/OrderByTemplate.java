package builder.select.order;

import builder.TerminalOperation;

public abstract class OrderByTemplate extends TerminalOperation {

    public OrderByTemplate(StringBuilder builder) {
        this.builder = builder;
    }

    public Order column(String columnName) {
        addCommaAfterFirstValue();
        append(columnName);
        return new Order(this.builder);
    }

    protected abstract void addCommaAfterFirstValue();
}
