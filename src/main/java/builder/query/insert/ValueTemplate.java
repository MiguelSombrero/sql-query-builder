package builder.query.insert;

import builder.appender.ValueAppender;
import query.Clause;

import java.math.BigDecimal;

public abstract class ValueTemplate extends TerminalClosingInsertOperation {

    public ValueTemplate(Clause clause) {
        super(clause);
    }

    /**
     * Validates user input and appends string 'value(s)'
     * into 'INSERT INTO table VALUE (value(s))' statement.
     *
     * @param value String value to be appended
     *
     * @return Value class which can be used to append
     * value (with comma in front) into 'VALUE (value(s))'
     * statement
     */
    public Value setString(String value) {
        addCommaAfterFirstValue();
        ValueAppender.appendStringParam(clause, value);
        return new Value(clause);
    }

    /**
     * Validates user input and appends integer 'value(s)'
     * into 'INSERT INTO table VALUE (value(s))' statement.
     *
     * @param value Int value to be appended
     *
     * @return Value class which can be used to append
     * value (with comma in front) into 'VALUE (value(s))'
     * statement
     */
    public Value setInt(int value) {
        addCommaAfterFirstValue();
        ValueAppender.appendIntParam(clause, value);
        return new Value(clause);
    }

    /**
     * Validates user input and appends integer 'value(s)'
     * into 'INSERT INTO table VALUE (value(s))' statement.
     *
     * @param value Long value to be appended
     *
     * @return Value class which can be used to append
     * value (with comma in front) into 'VALUE (value(s))'
     * statement
     */
    public Value setLong(long value) {
        addCommaAfterFirstValue();
        ValueAppender.appendLongParam(clause, value);
        return new Value(clause);
    }

    /**
     * Validates user input and appends double 'value(s)'
     * into 'INSERT INTO table VALUE (value(s))' statement.
     *
     * @param value Double value to be appended
     *
     * @return Value class which can be used to append
     * value (with comma in front) into 'VALUE (value(s))'
     * statement
     */
    public Value setDouble(double value) {
        addCommaAfterFirstValue();
        ValueAppender.appendDoubleParam(clause, value);
        return new Value(clause);
    }

    /**
     * Validates user input and appends double 'value(s)'
     * into 'INSERT INTO table VALUE (value(s))' statement.
     *
     * @param value BigDecimal value to be appended
     *
     * @return Value class which can be used to append
     * value (with comma in front) into 'VALUE (value(s))'
     * statement
     */
    public Value setBigDecimal(BigDecimal value) {
        addCommaAfterFirstValue();
        ValueAppender.appendBigDecimalParam(clause, value);
        return new Value(clause);
    }

    /**
     * Validates user input and appends integer 'value(s)'
     * into 'INSERT INTO table VALUE (value(s))' statement.
     *
     * @param value Date value to be appended
     *
     * @return Value class which can be used to append
     * value (with comma in front) into 'VALUE (value(s))'
     * statement
     */
    public Value setDate(String value) {
        addCommaAfterFirstValue();
        ValueAppender.appendDateParam(clause, value);
        return new Value(clause);
    }

    /**
     * Validates user input and appends integer 'value(s)'
     * into 'INSERT INTO table VALUE (value(s))' statement.
     *
     * @param value DateTime value to be appended
     *
     * @return Value class which can be used to append
     * value (with comma in front) into 'VALUE (value(s))'
     * statement
     */
    public Value setTimestamp(String value) {
        addCommaAfterFirstValue();
        ValueAppender.appendTimestampParam(clause, value);
        return new Value(clause);
    }

    /**
     * Validates user input and appends integer 'value(s)'
     * into 'INSERT INTO table VALUE (value(s))' statement.
     *
     * @param value Boolean value to be appended
     *
     * @return Value class which can be used to append
     * value (with comma in front) into 'VALUE (value(s))'
     * statement
     */
    public Value setBoolean(boolean value) {
        addCommaAfterFirstValue();
        ValueAppender.appendBooleanParam(clause, value);
        return new Value(clause);
    }

    /**
     * Validates user input and appends integer 'value(s)'
     * into 'INSERT INTO table VALUE (value(s))' statement.
     *
     * @param value Byte array value to be appended
     *
     * @return Value class which can be used to append
     * value (with comma in front) into 'VALUE (value(s))'
     * statement
     */
    public Value setByteArray(byte[] value) {
        addCommaAfterFirstValue();
        ValueAppender.appendByteArrayParam(clause, value);
        return new Value(clause);
    }

    protected abstract void addCommaAfterFirstValue();
}
