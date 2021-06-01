package builder.statement.select.column;

import builder.utils.StringAppender;
import query.SelectQuery;

public abstract class ColumnTemplate {
    private StringAppender stringAppender;
    private AggregateFunction aggregateFunction;
    protected SelectQuery query;

    public ColumnTemplate(SelectQuery query) {
        this.query = query;
        this.stringAppender = new StringAppender(query);
        this.aggregateFunction = new AggregateFunction(query);
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
        stringAppender.validateAndAppend(column);
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
        aggregateFunction.count(column);
        return new Column(query);
    }

    public Column countAll() {
        addCommaAfterFirstValue();
        aggregateFunction.countAll();
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
        aggregateFunction.min(column);
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
        aggregateFunction.max(column);
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
        aggregateFunction.avg(column);
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
        aggregateFunction.sum(column);
        return new Column(query);
    }

    protected abstract void addCommaAfterFirstValue();
}
