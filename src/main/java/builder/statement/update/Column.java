package builder.statement.update;

import builder.TerminalOperation;
import builder.condition.Condition;

public class Column extends TerminalOperation {

    public Column(StringBuilder queryString) {
        super(queryString);
    }

    public Value column(String column) {
        append(", ");
        append(column);
        append(" = ");
        return new Value(this.queryString);
    }

    public TerminalOperation where(Condition clause) {
        append(" WHERE ");
        append(clause.build());
        return new TerminalOperation(this.queryString);
    }
}
