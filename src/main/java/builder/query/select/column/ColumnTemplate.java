package builder.query.select.column;

import builder.appender.AggregateFunctionAppender;
import builder.appender.StringAppender;
import query.dql.SelectQuery;

public abstract class ColumnTemplate {
    private static AggregateFunctionAppender appender;

    protected SelectQuery query;

    public ColumnTemplate(SelectQuery query) {
        this.query = query;
    }

    public ToFrom all() {
        query.append("*");
        return new ToFrom(query);
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
        StringAppender.validateAndAppend(query, column);
        return new Column(query);
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
        appender.count(query, column);
        return new Column(query);
    }

    public Column countAll() {
        addCommaAfterFirstValue();
        appender.countAll(query);
        return new Column(query);
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
        appender.min(query, column);
        return new Column(query);
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
        appender.max(query, column);
        return new Column(query);
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
        appender.avg(query, column);
        return new Column(query);
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
        appender.sum(query, column);
        return new Column(query);
    }

    protected abstract void addCommaAfterFirstValue();
}
