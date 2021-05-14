package builder.statement.select.column;

import builder.statement.select.table.From;

/**
 * represents aliased column to be appended in
 * 'SELECT column(s) AS alias' statement.
 *
 * Adds comma before column name because it is not the first
 * column in a list of columns
 */
public class AliasedColumn extends ColumnTemplate {

    public AliasedColumn(StringBuilder queryString) {
        super(queryString);
    }

    /**
     * Appends 'FROM' to a query string.
     *
     * @return From class which is used to append tables
     *  or sub-queries in 'FROM table' statement.
     */
    public From from() {
        append(" FROM ");
        return new From(this.queryString);
    }

    @Override
    protected void addCommaAfterFirstValue() {
        append(", ");
    }
}
