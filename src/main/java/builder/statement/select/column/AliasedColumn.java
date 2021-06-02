package builder.statement.select.column;

import builder.statement.select.table.From;
import query.dql.DQLQuery;

public class AliasedColumn extends ColumnTemplate {

    public AliasedColumn(DQLQuery query) {
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
