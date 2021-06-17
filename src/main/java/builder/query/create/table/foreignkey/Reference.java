package builder.query.create.table.foreignkey;

import builder.appender.StringAppender;
import query.ddl.CreateQuery;

public class Reference {
    private CreateQuery query;

    public Reference(CreateQuery query) {
        this.query = query;
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
        StringAppender.validateAndAppend(query, ofTable);
        query.append("(");
        StringAppender.validateAndAppend(query, column);
        query.append(")");
        return new OnAction(query);
    }
}
