package builder.query.update;

import builder.clause.Condition;
import builder.appender.StringAppender;
import query.dml.UpdateQuery;

public class Column extends TerminalUpdateOperation {

    public Column(UpdateQuery query) {
        super(query);
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
        StringAppender.validateAndAppend(query, column);
        query.append(" = ");
        return new Value(query);
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
        query.append(" WHERE ");
        query.mergeClause(condition.build());
        return new TerminalUpdateOperation(query);
    }
}
