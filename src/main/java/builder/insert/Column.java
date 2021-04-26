package builder.insert;

import builder.TerminalOperation;

public class Column extends TerminalOperation {

    public Column(StringBuilder builder) {
        super(builder);
    }

    public Value column(String columns) {
        int index = firstIndexOfRightBracket();
        insert(index, columns);
        insert(index, ", ");
        return new Value(this.builder);
    }
}
