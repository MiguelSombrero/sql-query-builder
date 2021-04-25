package builder.select.order;

import builder.TerminalOperation;

public class OrderBy extends TerminalOperation {

    public OrderBy(StringBuilder builder) {
        this.builder = builder;
    }

    public OrderBy column(String columnName) {
        append(", ");
        append(columnName);
        return this;
    }
}
