package builder.appender;

import query.Query;

public class AggregateFunctionAppender {

    /**
     * Validates user input and appends 'COUNT(column)' into
     * query string 'SELECT aggregate function(s)'
     *
     * @param query Query object where string is appended
     *
     * @param column Column name to be appended in the COUNT() query'
     */
    public static void count(Query query, String column) {
        query.append("COUNT");
        applyAggregate(query, column);
    }

    /**
     * Appends 'COUNT(*)' into
     * query string 'SELECT aggregate function(s)'
     *
     * @param query Query object where string is appended
     */
    public static void countAll(Query query) {
        query.append("COUNT(*)");
    }

    /**
     * Validates user input and appends 'MIN(column)' into
     * query string 'SELECT aggregate function(s)'
     *
     * @param query Query object where string is appended
     *
     * @param column Column name to be appended in the MIN() query
     */
    public static void min(Query query, String column) {
        query.append("MIN");
        applyAggregate(query, column);
    }

    /**
     * Validates user input and appends 'MAX(column)' into
     * query string 'SELECT aggregate function(s)'
     *
     * @param query Query object where string is appended
     *
     * @param column Column name to be appended in the MAX() query
     */
    public static void max(Query query, String column) {
        query.append("MAX");
        applyAggregate(query, column);
    }

    /**
     * Validates user input and appends 'AVG(column)' into
     * query string 'SELECT aggregate function(s)'
     *
     * @param query Query object where string is appended
     *
     * @param column Column name to be appended in the AVG() query
     */
    public static void avg(Query query, String column) {
        query.append("AVG");
        applyAggregate(query, column);
    }

    /**
     * Validates user input and appends 'SUM(column)' into
     * query string 'SELECT aggregate function(s)'
     *
     * @param query Query object where string is appended
     *
     * @param column Column name to be appended in the SUM() query
     */
    public static void sum(Query query, String column) {
        query.append("SUM");
        applyAggregate(query, column);
    }

    private static void applyAggregate(Query query, String toColumn) {
        query.append("(");
        StringAppender.validateAndAppend(query, toColumn);
        query.append(")");
    }

}
