package builder.statement.select.column;

import builder.query.Query;
import builder.statement.select.table.From;

public class AliasedColumn extends ColumnTemplate {

    public AliasedColumn(Query query) {
        super(query);
    }

    /**
     * Appends 'FROM' to a query string 'SELECT column(s) FROM'.
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
