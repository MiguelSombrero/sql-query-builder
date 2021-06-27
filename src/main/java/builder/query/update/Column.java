package builder.query.update;

import builder.statement.Condition;
import builder.appender.StringAppender;
import query.Statement;

public class Column extends TerminalUpdateOperation {

    public Column(Statement statement) {
        super(statement);
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
        statement.append(", ");
        StringAppender.validateAndAppend(statement, column);
        statement.append(" = ");
        return new Value(statement);
    }

    /**
     * Appends 'WHERE condition' into UPDATE statement.
     *
     * @param condition Condition to append in UPDATE statement
     *
     * @return TerminalUpdateOperation which can be used only
     * to terminate query building
     */
    public TerminalUpdateOperation where(Condition condition) {
        statement.append(" WHERE ");
        statement.mergeStatement(condition.build());
        return new TerminalUpdateOperation(statement);
    }
}
