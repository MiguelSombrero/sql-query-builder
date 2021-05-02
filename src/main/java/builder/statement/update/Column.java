package builder.statement.update;

import builder.TerminalOperation;
import builder.clause.where.Negation;

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

    public Negation where(String value) {
        append(" WHERE ");
        append(value);
        return new Negation(this.builder);
    }
}
