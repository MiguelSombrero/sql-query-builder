package builder.statement.select.order;

import builder.TerminalOperation;

public class GroupBy extends TerminalOperation {

    public GroupBy(StringBuilder queryString) {
        super(queryString);
    }

    public Having column(String columnName) {
        validateAndAppend(columnName);
        return new Having(this.queryString);
    }
}
