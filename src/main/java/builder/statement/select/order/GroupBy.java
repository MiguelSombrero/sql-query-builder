package builder.statement.select.order;

import builder.TerminalOperation;

public class GroupBy extends TerminalOperation {

    public GroupBy(StringBuilder builder) {
        super(builder);
    }

    public Having column(String columnName) {
        append(columnName);
        return new Having(this.builder);
    }
}
