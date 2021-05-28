package builder.statement.create.table.foreignkey;

import builder.Query;
import builder.statement.create.TerminalCreateOperation;
import builder.utils.StringAppender;

public class ForeignKey extends TerminalCreateOperation {
    protected StringAppender stringAppender;

    public ForeignKey(Query query) {
        super(query);
        this.stringAppender = new StringAppender(query);
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
        stringAppender.validateAndAppend(column);
        query.append(")");
        return new Reference(query);
    }
}
