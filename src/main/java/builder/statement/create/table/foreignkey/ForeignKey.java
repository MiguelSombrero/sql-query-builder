package builder.statement.create.table.foreignkey;

import builder.statement.create.TerminalCreateOperation;

import javax.xml.bind.ValidationException;

public class ForeignKey extends TerminalCreateOperation {

    public ForeignKey(StringBuilder queryString) {
        super(queryString);
    }

    public Reference foreignKey(String column) throws ValidationException {
        append(", FOREIGN KEY (");
        validateAndAppend(column);
        append(")");
        return new Reference(this.queryString);
    }
}
