package builder.query.create.table.foreignkey;

import builder.appender.StringAppender;
import query.Statement;

public class Reference {
    private Statement statement;

    public Reference(Statement statement) {
        this.statement = statement;
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
        statement.append(" REFERENCES ");
        StringAppender.validateAndAppend(statement, ofTable);
        statement.append("(");
        StringAppender.validateAndAppend(statement, column);
        statement.append(")");
        return new OnAction(statement);
    }
}
