package builder.statement.create;

import builder.TerminalOperation;

public class ForeignKey extends TerminalOperation {

    public ForeignKey(StringBuilder builder) {
        super(builder);
    }

    public Reference foreignKey(String columnName) {
        int index = lastIndexOfRightBracket();
        insert(index, ")");
        insert(index, columnName);
        insert(index,", FOREIGN KEY (");
        return new Reference(this.builder);
    }
}
