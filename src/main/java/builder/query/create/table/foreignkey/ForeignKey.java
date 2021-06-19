package builder.query.create.table.foreignkey;

import builder.query.create.TerminalClosingCreateOperation;
import builder.appender.StringAppender;
import query.CreateQuery;

public class ForeignKey extends TerminalClosingCreateOperation {

    public ForeignKey(CreateQuery query) {
        super(query);
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
        query.append(", FOREIGN KEY (");
        StringAppender.validateAndAppend(query, column);
        query.append(")");
        return new Reference(query);
    }
}
