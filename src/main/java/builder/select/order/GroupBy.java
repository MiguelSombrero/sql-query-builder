package builder.select.order;

import builder.TerminalOperation;

public class GroupBy extends TerminalOperation {

    public GroupBy(StringBuilder builder) {
        this.builder = builder;
    }

    public GroupBy column(String columnName) {
        append(", ");
        append(columnName);
        return this;
    }

    public FirstOrderBy orderBy() {
        append(" ORDER BY ");
        return new FirstOrderBy(this.builder);
    }
}
