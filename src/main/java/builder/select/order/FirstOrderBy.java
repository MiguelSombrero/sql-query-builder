package builder.select.order;

import builder.TerminalOperation;

public class FirstOrderBy extends TerminalOperation {

    public FirstOrderBy(StringBuilder builder) {
        this.builder = builder;
    }

    public OrderBy column(String columnName) {
        append(columnName);
        return new OrderBy(this.builder);
    }
}
