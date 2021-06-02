package builder.statement.update;

import builder.TerminalDMLOperation;
import query.dml.DMLQuery;
import builder.condition.Condition;
import builder.utils.StringAppender;

public class Column extends TerminalDMLOperation {
    private StringAppender stringAppender;

    public Column(DMLQuery query) {
        super(query);
        this.stringAppender = new StringAppender(query);
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
        query.append(", ");
        stringAppender.validateAndAppend(column);
        query.append(" = ");
        return new Value(query);
    }

    /**
     * Appends 'WHERE condition' into UPDATE statement.
     *
     * @param condition Condition to append in UPDATE statement
     *
     * @return TerminalOperation which can be used only
     * to terminate query building
     */
    public TerminalDMLOperation where(Condition condition) {
        query.append(" WHERE ");
        query.append(condition.build().toString());
        return new TerminalDMLOperation(query);
    }
}
