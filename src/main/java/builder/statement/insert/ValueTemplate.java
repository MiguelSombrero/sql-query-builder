package builder.statement.insert;

import builder.appender.ValueAppender;
import query.dml.InsertQuery;

public abstract class ValueTemplate extends TerminalClosingInsertOperation {

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
        ValueAppender.appendStringParam(query, value);
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
        ValueAppender.appendIntParam(query, value);
        return new Value(query);
    }

    public Value setLong(long value) {
        addCommaAfterFirstValue();
        ValueAppender.appendLongParam(query, value);
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
        ValueAppender.appendDoubleParam(query, value);
        return new Value(query);
    }

    public Value setDate(String value) {
        addCommaAfterFirstValue();
        ValueAppender.appendDateParam(query, value);
        return new Value(query);
    }

    public Value setDateTime(String value) {
        addCommaAfterFirstValue();
        ValueAppender.appendDateTimeParam(query, value);
        return new Value(query);
    }

    protected abstract void addCommaAfterFirstValue();
}
