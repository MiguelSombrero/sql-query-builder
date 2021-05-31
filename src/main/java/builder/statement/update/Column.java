package builder.statement.update;

import query.SQLQuery;
import builder.TerminalOperation;
import builder.condition.Condition;
import builder.utils.StringAppender;

public class Column extends TerminalOperation {
    private StringAppender stringAppender;

    public Column(SQLQuery SQLQuery) {
        super(SQLQuery);
        this.stringAppender = new StringAppender(SQLQuery);
    }

    /**
     * Validates user input and appends ', column2 =' into
     * 'UPDATE table SET column1 = value1, column2 = value2' statement.
     *
     * @param column Column name to be appended into UPDATE query
     *
     * @return Value class which is used to append value
     * into selected column
     */
    public Value column(String column) {
        SQLQuery.append(", ");
        stringAppender.validateAndAppend(column);
        SQLQuery.append(" = ");
        return new Value(SQLQuery);
    }

    /**
     * Appends 'WHERE condition' into UPDATE statement.
     *
     * @param condition Condition to append in UPDATE statement
     *
     * @return TerminalOperation which can be used only
     * to terminate query building
     */
    public TerminalOperation where(Condition condition) {
        SQLQuery.append(" WHERE ");
        SQLQuery.append(condition.build());
        return new TerminalOperation(SQLQuery);
    }
}
