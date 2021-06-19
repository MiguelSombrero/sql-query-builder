package builder.query.select.column;

import query.SelectQuery;

/**
 * represents the first column to be appended in
 * 'SELECT column(s), aggregate function(s)' statement.
 *
 * Does not add comma before column because it is the first
 * column in a list of columns
 */
public class FirstColumn extends ColumnTemplate {

    public FirstColumn(SelectQuery query) {
        super(query);
    }

    @Override
    protected void addCommaAfterFirstValue() {
    }
}
