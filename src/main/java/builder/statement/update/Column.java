package builder.statement.update;

import builder.TerminalOperation;
import builder.condition.Condition;

import javax.xml.bind.ValidationException;

public class Column extends TerminalOperation {

    public Column(StringBuilder queryString) {
        super(queryString);
    }

    public Value column(String column) throws ValidationException {
        append(", ");
        validateAndAppend(column);
        append(" = ");
        return new Value(this.queryString);
    }

    public TerminalOperation where(Condition clause) {
        append(" WHERE ");
        append(clause.build());
        return new TerminalOperation(this.queryString);
    }
}
