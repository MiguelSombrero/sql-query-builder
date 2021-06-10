package builder.statement.insert;

import builder.appender.ValueAppender;
import query.dml.InsertQuery;

public abstract class ValueTemplate extends TerminalClosingInsertOperation {
    private static ValueAppender valueAppender;

    public ValueTemplate(InsertQuery query) {
        super(query);
    }

    /**
     * Validates user input and appends string 'value(s)'
     * into 'INSERT INTO table VALUE (value(s))' statement.
     *
     * @param value Value to be appended
     *
     * @return Value class which can be used to append
     * value (with comma in front) into 'VALUE (value(s))'
     * statement
     */
    public Value setString(String value) {
        addCommaAfterFirstValue();
        valueAppender.appendStringParam(query, value);
        return new Value(query);
    }

    /**
     * Validates user input and appends integer 'value(s)'
     * into 'INSERT INTO table VALUE (value(s))' statement.
     *
     * @param value Value to be appended
     *
     * @return Value class which can be used to append
     * value (with comma in front) into 'VALUE (value(s))'
     * statement
     */
    public Value setInt(int value) {
        addCommaAfterFirstValue();
        valueAppender.appendIntParam(query, value);
        return new Value(query);
    }

    /**
     * Validates user input and appends double 'value(s)'
     * into 'INSERT INTO table VALUE (value(s))' statement.
     *
     * @param value Value to be appended
     *
     * @return Value class which can be used to append
     * value (with comma in front) into 'VALUE (value(s))'
     * statement
     */
    public Value setDouble(double value) {
        addCommaAfterFirstValue();
        query.append(value);
        return new Value(query);
    }

    protected abstract void addCommaAfterFirstValue();
}
