package builder.statement.insert;

import utils.StringValueAppender;
import query.dml.InsertQuery;

public abstract class ValueTemplate extends TerminalClosingInsertOperation {
    private StringValueAppender stringValueAppender;

    public ValueTemplate(InsertQuery query) {
        super(query);
        this.stringValueAppender = new StringValueAppender(query);
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
    public Value value(String value) {
        addCommaAfterFirstValue();
        stringValueAppender.validateAndAppendStringValue(value);
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
    public Value value(int value) {
        addCommaAfterFirstValue();
        query.append(value);
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
    public Value value(double value) {
        addCommaAfterFirstValue();
        query.append(value);
        return new Value(query);
    }

    protected abstract void addCommaAfterFirstValue();
}
