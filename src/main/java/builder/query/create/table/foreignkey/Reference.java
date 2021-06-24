package builder.query.create.table.foreignkey;

import builder.appender.StringAppender;
import query.Clause;

public class Reference {
    private Clause clause;

    public Reference(Clause clause) {
        this.clause = clause;
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
        clause.append(" REFERENCES ");
        StringAppender.validateAndAppend(clause, ofTable);
        clause.append("(");
        StringAppender.validateAndAppend(clause, column);
        clause.append(")");
        return new OnAction(clause);
    }
}
