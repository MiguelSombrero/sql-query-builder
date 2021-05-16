package builder.statement.select.order;

import builder.TerminalOperation;

import javax.xml.bind.ValidationException;

public class GroupBy extends TerminalOperation {

    public GroupBy(StringBuilder queryString) {
        super(queryString);
    }

    public Having column(String columnName) throws ValidationException {
        validateAndAppend(columnName);
        return new Having(this.queryString);
    }
}
