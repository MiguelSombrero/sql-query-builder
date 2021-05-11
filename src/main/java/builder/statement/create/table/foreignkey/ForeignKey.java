package builder.statement.create.table.foreignkey;

import builder.statement.create.TerminalCreateOperation;

public class ForeignKey extends TerminalCreateOperation {

    public ForeignKey(StringBuilder builder) {
        super(builder);
    }

    public Reference foreignKey(String column) {
        append(", FOREIGN KEY (");
        append(column);
        append(")");
        return new Reference(this.builder);
    }
}
