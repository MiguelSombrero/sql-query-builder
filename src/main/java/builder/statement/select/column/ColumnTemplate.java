package builder.statement.select.column;

import builder.Query;
import builder.utils.ColumnStringAppender;

public abstract class ColumnTemplate {
    private ColumnStringAppender columnStringAppender;

    protected Query query;

    public ColumnTemplate(Query query) {
        this.query = query;
        this.columnStringAppender = new ColumnStringAppender(query);
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
        columnStringAppender.validateAndAppend(column);
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
        return applyAggregate("COUNT", column);
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
        return applyAggregate("MIN", column);
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
        return applyAggregate("MAX", column);
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
        return applyAggregate("AVG", column);
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
        return applyAggregate("SUM", column);
    }

    private Column applyAggregate(String function, String toColumn) {
        addCommaAfterFirstValue();
        query.append(function);
        query.append("(");
        columnStringAppender.validateAndAppend(toColumn);
        query.append(")");
        return new Column(query);
    }

    protected abstract void addCommaAfterFirstValue();
}
