package builder.statement.create.table.foreignkey;

import builder.utils.StringAppender;
import query.ddl.CreateQuery;

public class Reference {
    private StringAppender stringAppender;

    private CreateQuery query;

    public Reference(CreateQuery query) {
        this.query = query;
        this.stringAppender = new StringAppender(query);
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
        query.append(" REFERENCES ");
        stringAppender.validateAndAppend(ofTable);
        query.append("(");
        stringAppender.validateAndAppend(column);
        query.append(")");
        return new OnAction(query);
    }
}
