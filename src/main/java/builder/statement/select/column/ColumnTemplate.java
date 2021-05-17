package builder.statement.select.column;

import builder.SQLStringAppender;

/**
 * Defines the methods for appending columns or aggregate functions in
 * 'SELECT column(s), aggregate function(s)' statement.
 */
public abstract class ColumnTemplate extends SQLStringAppender {

    public ColumnTemplate(StringBuilder queryString) {
        super(queryString);
    }

    /**
     * Appends 'column' into query string 'SELECT column(s)'
     *
     * @param column Column name to be appended in the query
     * @return Column class which can be used to append more columns,
     * aggregate functions and alias selected columns or call 'FROM table'
     */
    public Column column(String column) {
        addCommaAfterFirstValue();
        validateAndAppend(column);
        return new Column(this.queryString);
    }

    /**
     * Appends 'COUNT(column)' into query string 'SELECT aggregate function(s)'
     *
     * @param column Column name to be appended in the COUNT() query
     * @return Column class which can be used to append more columns,
     * aggregate functions and alias selected columns or call 'FROM table'
     */
    public Column count(String column) {
        return applyAggregate("COUNT", column);
    }

    /**
     * Appends 'MIN(column)' into query string 'SELECT aggregate function(s)'
     *
     * @param column Column name to be appended in the MIN() query
     * @return Column class which can be used to append more columns,
     * aggregate functions and alias selected columns or call 'FROM table'
     */
    public Column min(String column) {
        return applyAggregate("MIN", column);
    }

    /**
     * Appends 'MAX(column)' into query string 'SELECT aggregate function(s)'
     *
     * @param column Column name to be appended in the MAX() query
     * @return Column class which can be used to append more columns,
     * aggregate functions and alias selected columns or call 'FROM table'
     */
    public Column max(String column) {
        return applyAggregate("MAX", column);
    }

    /**
     * Appends 'AVG(column)' into query string 'SELECT aggregate function(s)'
     *
     * @param column Column name to be appended in the AVG() query
     * @return Column class which can be used to append more columns,
     * aggregate functions and alias selected columns or call 'FROM table'
     */
    public Column avg(String column) {
        return applyAggregate("AVG", column);
    }

    /**
     * Appends 'SUM(column)' into query string 'SELECT aggregate function(s)'
     *
     * @param column Column name to be appended in the SUM() query
     * @return Column class which can be used to append more columns,
     * aggregate functions and alias selected columns or call 'FROM table'
     */
    public Column sum(String column) {
        return applyAggregate("SUM", column);
    }

    private Column applyAggregate(String function, String toColumn) {
        addCommaAfterFirstValue();
        validateAndAppend(function);
        append("(");
        validateAndAppend(toColumn);
        append(")");
        return new Column(this.queryString);
    }

    protected abstract void addCommaAfterFirstValue();
}
