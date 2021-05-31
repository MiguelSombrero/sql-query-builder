package builder.statement.create.table.foreignkey;

import query.SQLQuery;
import builder.utils.StringAppender;

public class Reference {
    private StringAppender stringAppender;

    private SQLQuery SQLQuery;

    public Reference(SQLQuery SQLQuery) {
        this.SQLQuery = SQLQuery;
        this.stringAppender = new StringAppender(SQLQuery);
    }

    /**
     * Validates user input and appends 'REFERENCES Table(column)'
     * into 'FOREIGN KEY (column) REFERENCES Table(column)' statement.
     *
     * @param column name foreign key is referencing
     *
     * @param ofTable name foreign key is referencing
     *
     * @return OnAction class which can be used to
     * assing actions on foreign key
     */
    public OnAction references(String column, String ofTable) {
        SQLQuery.append(" REFERENCES ");
        stringAppender.validateAndAppend(ofTable);
        SQLQuery.append("(");
        stringAppender.validateAndAppend(column);
        SQLQuery.append(")");
        return new OnAction(SQLQuery);
    }
}
