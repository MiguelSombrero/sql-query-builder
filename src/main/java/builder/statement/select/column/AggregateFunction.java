package builder.statement.select.column;

import builder.utils.ColumnStringAppender;
import query.SelectQuery;

public class AggregateFunction {
    private ColumnStringAppender columnStringAppender;
    private SelectQuery query;

    public AggregateFunction(SelectQuery query) {
        this.query = query;
        this.columnStringAppender = new ColumnStringAppender(query);
    }

    /**
     * Validates user input and appends 'COUNT(column)' into
     * query string 'SELECT aggregate function(s)'
     *
     * @param column Column name to be appended in the COUNT() queryble'
     */
    public void count(String column) {
        applyAggregate("COUNT", column);
    }

    public void countAll() {
        applyAll("COUNT");
    }

    /**
     * Validates user input and appends 'MIN(column)' into
     * query string 'SELECT aggregate function(s)'
     *
     * @param column Column name to be appended in the MIN() query
     */
    public void min(String column) {
        applyAggregate("MIN", column);
    }

    /**
     * Validates user input and appends 'MAX(column)' into
     * query string 'SELECT aggregate function(s)'
     *
     * @param column Column name to be appended in the MAX() query
     */
    public void max(String column) {
        applyAggregate("MAX", column);
    }

    /**
     * Validates user input and appends 'AVG(column)' into
     * query string 'SELECT aggregate function(s)'
     *
     * @param column Column name to be appended in the AVG() query
     */
    public void avg(String column) {
        applyAggregate("AVG", column);
    }

    /**
     * Validates user input and appends 'SUM(column)' into
     * query string 'SELECT aggregate function(s)'
     *
     * @param column Column name to be appended in the SUM() query
     */
    public void sum(String column) {
        applyAggregate("SUM", column);
    }

    private void applyAggregate(String function, String toColumn) {
        query.append(function);
        query.append("(");
        columnStringAppender.validateAndAppend(toColumn);
        query.append(")");
    }

    private void applyAll(String function) {
        query.append(function);
        query.append("(*)");
    }

}
