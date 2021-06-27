package builder.query.create.table.foreignkey;

import builder.query.create.TerminalClosingCreateOperation;
import builder.appender.StringAppender;
import query.Statement;

public class ForeignKey extends TerminalClosingCreateOperation {

    public ForeignKey(Statement statement) {
        super(statement);
    }

    /**
     * Validates user input and appends 'FOREIGN KEY column'
     * into 'FOREIGN KEY (column) REFERENCES Table(column)' statement.
     *
     * @param column Column name foreign key is assigned to
     *
     * @return Reference class which is used to append
     * REFERENCE to table and column
     */
    public Reference foreignKey(String column) {
        statement.append(", FOREIGN KEY (");
        StringAppender.validateAndAppend(statement, column);
        statement.append(")");
        return new Reference(statement);
    }
}
