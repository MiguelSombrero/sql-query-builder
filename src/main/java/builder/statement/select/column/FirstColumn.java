package builder.statement.select.column;

import builder.Query;

/**
 * represents the first column to be appended in
 * 'SELECT column(s), aggregate function(s)' statement.
 *
 * Does not add comma before column because it is the first
 * column in a list of columns
 */
public class FirstColumn extends ColumnTemplate {

    public FirstColumn(Query query) {
        super(query);
    }

    @Override
    protected void addCommaAfterFirstValue() {
    }
}
