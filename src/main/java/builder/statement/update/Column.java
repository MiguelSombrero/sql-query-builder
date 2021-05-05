package builder.statement.update;

import builder.TerminalOperation;
import builder.clause.where.Conjunction;

public class Column extends TerminalOperation {

    public Column(StringBuilder builder) {
        super(builder);
    }

    public Value column(String column) {
        append(", ");
        append(column);
        append(" = ");
        return new Value(this.builder);
    }

    public TerminalOperation where(Conjunction clause) {
        append(clause.build());
        return new TerminalOperation(this.builder);
    }
}
