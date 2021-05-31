package builder.statement.create.table.foreignkey;

import query.SQLQuery;
import builder.statement.create.TerminalCreateOperation;
import builder.utils.StringAppender;

public class ForeignKey extends TerminalCreateOperation {
    protected StringAppender stringAppender;

    public ForeignKey(SQLQuery SQLQuery) {
        super(SQLQuery);
        this.stringAppender = new StringAppender(SQLQuery);
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
        SQLQuery.append(", FOREIGN KEY (");
        stringAppender.validateAndAppend(column);
        SQLQuery.append(")");
        return new Reference(SQLQuery);
    }
}
