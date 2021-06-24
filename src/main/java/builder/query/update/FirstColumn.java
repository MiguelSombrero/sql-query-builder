package builder.query.update;

import builder.appender.StringAppender;
import query.Clause;

public class FirstColumn {
    private Clause clause;

    public FirstColumn(Clause clause) {
        this.clause = clause;
    }

    /**
     * Validates user input and appends 'column1 =' into
     * 'UPDATE table SET column1 = value1, ...' statement.
     *
     * @param column Column name to be appended into UPDATE query
     *
     * @return Value class which is used to append value
     * into selected column
     */
    public Value column(String column) {
        StringAppender.validateAndAppend(clause, column);
        clause.append(" = ");
        return new Value(clause);
    }
}
