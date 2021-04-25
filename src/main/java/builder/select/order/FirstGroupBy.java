package builder.select.order;

import builder.TerminalOperation;

public class FirstGroupBy extends TerminalOperation {

    public FirstGroupBy(StringBuilder builder) {
        this.builder = builder;
    }

    public GroupBy column(String columnName) {
        append(columnName);
        return new GroupBy(this.builder);
    }
}
