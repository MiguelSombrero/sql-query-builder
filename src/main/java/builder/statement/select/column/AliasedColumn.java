package builder.statement.select.column;

import builder.query.Query;
import builder.statement.select.table.From;

/**
 * represents aliased column to be appended in
 * 'SELECT column(s) AS alias' statement.
 *
 * Adds comma before column name because it is not the first
 * column in a list of columns
 */
public class AliasedColumn extends ColumnTemplate {

    public AliasedColumn(Query query) {
        super(query);
    }

    /**
     * Appends 'FROM' to a query string.
     *
     * @return From class which is used to append tables
     *  or sub-queries in 'FROM table' statement.
     */
    public From from() {
        query.append(" FROM ");
        return new From(query);
    }

    @Override
    protected void addCommaAfterFirstValue() {
        query.append(", ");
    }
}
