package builder.query.select.column;

import builder.appender.AggregateFunctionAppender;
import builder.appender.StringAppender;
import query.Statement;

public abstract class ColumnTemplate {
    
    protected Statement statement;

    public ColumnTemplate(Statement statement) {
        this.statement = statement;
    }

    /**
     * Appends '*' into query string 'SELECT *'
     *
     * @return ToFrom class which can be used to proceed
     * appending 'FROM' in SELECT statement. No columns can
     * be selected to SELECT statement after this
     */
    public ToFrom all() {
        statement.append("*");
        return new ToFrom(statement);
    }

    /**
     * Validates user input and appends 'column'
     * into query string 'SELECT column(s)'
     *
     * @param column Column name to be appended in the query
     *
     * @return Column class which can be used to append more columns,
     * aggregate functions and alias selected columns or call 'FROM table'
     */
    public Column column(String column) {
        addCommaAfterFirstValue();
        StringAppender.validateAndAppend(statement, column);
        return new Column(statement);
    }

    /**
     * Validates user input and appends 'COUNT(column)' into
     * query string 'SELECT aggregate function(s)'
     *
     * @param column Column name to be appended in the COUNT() query
     *
     * @return Column class which can be used to append more columns,
     * aggregate functions and alias selected columns or call 'FROM table'
     */
    public Column count(String column) {
        addCommaAfterFirstValue();
        AggregateFunctionAppender.count(statement, column);
        return new Column(statement);
    }

    /**
     * Appends 'COUNT(*)' into query string 'SELECT COUNT(*)'.
     *
     * @return Column class which can be used to append more columns,
     * aggregate functions and alias selected columns or call 'FROM table'
     */
    public Column countAll() {
        addCommaAfterFirstValue();
        AggregateFunctionAppender.countAll(statement);
        return new Column(statement);
    }

    /**
     * Validates user input and appends 'MIN(column)' into
     * query string 'SELECT aggregate function(s)'
     *
     * @param column Column name to be appended in the MIN() query
     *
     * @return Column class which can be used to append more columns,
     * aggregate functions and alias selected columns or call 'FROM table'
     */
    public Column min(String column) {
        addCommaAfterFirstValue();
        AggregateFunctionAppender.min(statement, column);
        return new Column(statement);
    }

    /**
     * Validates user input and appends 'MAX(column)' into
     * query string 'SELECT aggregate function(s)'
     *
     * @param column Column name to be appended in the MAX() query
     *
     * @return Column class which can be used to append more columns,
     * aggregate functions and alias selected columns or call 'FROM table'
     */
    public Column max(String column) {
        addCommaAfterFirstValue();
        AggregateFunctionAppender.max(statement, column);
        return new Column(statement);
    }

    /**
     * Validates user input and appends 'AVG(column)' into
     * query string 'SELECT aggregate function(s)'
     *
     * @param column Column name to be appended in the AVG() query
     *
     * @return Column class which can be used to append more columns,
     * aggregate functions and alias selected columns or call 'FROM table'
     */
    public Column avg(String column) {
        addCommaAfterFirstValue();
        AggregateFunctionAppender.avg(statement, column);
        return new Column(statement);
    }

    /**
     * Validates user input and appends 'SUM(column)' into
     * query string 'SELECT aggregate function(s)'
     *
     * @param column Column name to be appended in the SUM() query
     *
     * @return Column class which can be used to append more columns,
     * aggregate functions and alias selected columns or call 'FROM table'
     */
    public Column sum(String column) {
        addCommaAfterFirstValue();
        AggregateFunctionAppender.sum(statement, column);
        return new Column(statement);
    }

    protected abstract void addCommaAfterFirstValue();
}
